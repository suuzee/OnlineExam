package com.speedy.base.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import com.speedy.base.common.JetException;

import jxl.*;
import jxl.write.*;

public class FileUtil {
	public static File createFile(String fileName) throws IOException {
		File file = new File(fileName);
		File dir = file.getParentFile();
		dir.mkdirs();
		file.createNewFile();
		return file;
	}

	public static String getPath(String fileName) throws IOException {
		File file = new File(fileName);
		File dir = file.getParentFile();
		return dir.getAbsolutePath() + File.separator;
	}
	public static boolean checkDirectoryModify(String path, long lastModify) {
		File dir = new File(path);
		Stack fileStack = new Stack();
		fileStack.push(dir);
		while (!fileStack.empty()) {
			File file = (File) fileStack.pop();
			if (file.lastModified() > lastModify)
				return true;
			if (file.isFile())
				continue;
			File[] files = file.listFiles();
			if(files == null)
				continue;
			for (int i = 0; i < files.length; i++) {
				fileStack.push(files[i]);
			}
		}
		return false;
	}

	public static List getAllFile(String path, String format, boolean alllevel) throws IOException {
		File directory = new File(path);
		ArrayList ret = new ArrayList();

		Stack fileStack = new Stack();
		fileStack.push(directory);
		while (!fileStack.empty()) {
			File file = (File) fileStack.pop();
			if (file.isFile()) {
				String fileName = file.toString();
				if (format==null||fileName.endsWith(format))
					ret.add(fileName);
				continue;
			} else if (alllevel) {
				File[] files = file.listFiles();
				if(files == null)
					continue;
				for (int i = 0; i < files.length; i++) {
					fileStack.push(files[i]);
				}
			}
		}
		return ret;
	}

	public static void copyFile(String src, String desc) throws IOException {
		FileInputStream input = new FileInputStream(src);
		FileOutputStream output = new FileOutputStream(desc);
		byte[] b = new byte[1024 * 64];
		int len;
		while ((len = input.read(b)) != -1)
			output.write(b, 0, len);
		output.flush();
		output.close();
		input.close();
	}

	public static void copyDirectory(String src, String desc) throws IOException {
		(new File(desc)).mkdirs();
		File[] file = (new File(src)).listFiles();
		if (file == null)
			return;
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile())
				copyFile(src + "/" + file[i].getName(), desc + "/" + file[i].getName());
			if (file[i].isDirectory())
				copyDirectory(src + "/" + file[i].getName(), desc + "/" + file[i].getName());
		}
	}

	public static void deleteDirectory(String path) throws IOException {
		File directory = new File(path);
		if (!directory.exists())
			return;
		Stack fileStack = new Stack();
		fileStack.push(directory);
		while (!fileStack.empty()) {
			File file = (File) fileStack.pop();
			if (file.isFile())
				file.delete();
			else {
				File[] files = file.listFiles();
				if (files == null || files.length <= 0) {
					file.delete();
					continue;
				}
				fileStack.push(file);
				for (int i = 0; i < files.length; i++) {
					fileStack.push(files[i]);
				}
			}
		}
	}

	public static void deleteFile(String file) throws IOException {
		File f = new File(file);
		f.delete();
	}

	public static boolean exist(String file) throws IOException {
		File f = new File(file);
		return f.exists();
	}

	// 对于参数sheet, x, y, 0代表第一个
	public static String[][] readExcelData(String file, int sheet, int x, int y, int width, int height)
			throws JetException {
		try {
			InputStream is = new FileInputStream(file);
			Workbook rwb = Workbook.getWorkbook(is);
			Sheet rSheet = rwb.getSheet(sheet);

			String[][] ret = new String[height][width];
			int colSize = rSheet.getColumns();
			int rowSize = rSheet.getRows();
			for (int row = 0; row < height; row++) {
				if (row + y >= rowSize)
					break;
				for (int col = 0; col < width; col++) {
					if (col + x >= colSize)
						break;
					Cell cell = rSheet.getCell(col + x, row + y);
					ret[row][col] = getCellValue(cell);
				}
			}
			rwb.close();
			return ret;
		} catch (Exception e) {
			throw new JetException(e);
		}
	}

	private static String getCellValue(Cell cell) {
		String value = null;
		if (cell.getType() == CellType.LABEL) {
			LabelCell label = (LabelCell) cell;
			value = label.getString();
		} else if (cell.getType() == CellType.NUMBER) {
			NumberCell num = (NumberCell) cell;
			value = Double.toString(num.getValue());
		} else if (cell.getType() == CellType.DATE) {
			DateCell date = (DateCell) cell;
			java.sql.Date d = new java.sql.Date(date.getDate().getTime());
			value = d.toString();
		} else
			value = cell.getContents();
		return value;
	}

	public static String[][] readExcelDatas(String file, int sheet, int x, int y) throws JetException {
		try {
			InputStream is = new FileInputStream(file);
			Workbook rwb = Workbook.getWorkbook(is);
			Sheet rSheet = rwb.getSheet(sheet);
			int colSize = rSheet.getColumns();
			int rowSize = rSheet.getRows();
			String[][] ret = new String[rowSize - y][colSize - x];

			for (int row = 0; row < rowSize; row++) {
				if (row + y >= rowSize)
					break;
				for (int col = 0; col < colSize; col++) {
					if (col + x >= colSize)
						break;
					Cell cell = rSheet.getCell(col + x, row + y);
					ret[row][col] = getCellValue(cell);
				}
			}
			rwb.close();
			return ret;
		} catch (Exception e) {
			throw new JetException(e);
		}

	}

	private static WritableCellFormat cf = new WritableCellFormat();
	private final static int NUMBER_TYPE = 1;
	private final static int STRING_TYPE = 0;

	/*
	 * 其中dformat为数字或者字符格式：1表示数据，0表示字符
	 */
	public static void writeExcelData(String file, String sheetName, int x, int y, String[][] data, String titles[],
			int formats[]) throws JetException {
		try {
			WritableWorkbook wwb = null;
			File pfile = new File(file);
			// 如果文件存在
			if (pfile.length() != 0) {
				Workbook wb = Workbook.getWorkbook(pfile);
				wwb = Workbook.createWorkbook(pfile, wb);
			} else
				wwb = Workbook.createWorkbook(pfile);

			int sheet = wwb.getNumberOfSheets();
			if (sheetName == null)
				sheetName = "";
			WritableSheet ws = wwb.createSheet(sheetName, sheet + 1);

			int srow = 0;
			if (titles != null) {
				for (int col = 0; titles != null && col < titles.length; col++) {
					Label label = new Label(col + x, y, titles[col]);
					ws.addCell(label);
				}
				srow = 1;
			}
			for (int row = 0; data != null && row < data.length; row++) {
				for (int col = 0; data[row] != null && col < data[row].length; col++) {
					if (data[row][col] == null)
						continue;

					if (formats != null && formats.length > col && formats[col] == NUMBER_TYPE) {
						jxl.write.Number num = new jxl.write.Number(col + x, row + srow + y, StringUtil
								.parseDouble(data[row][col]), cf);
						ws.addCell(num);
					} else {
						Label label = new Label(col + x, row + srow + y, data[row][col]);
						ws.addCell(label);
					}
				}
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			throw new JetException(e);
		}
	}

	public static void writeExcelData(String file, String sheetName, int x, int y, String[][] data) {
		try {
			writeExcelData(file, sheetName, x, y, data, null, null);
		} catch (Exception e) {
		}
	}

	public static void writeExcelDatas(String file, Hashtable datas) throws JetException {
		try {
			OutputStream os = new FileOutputStream(file);
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			int sheet = wwb.getNumberOfSheets();
			Enumeration keys = datas.keys();
			while (keys.hasMoreElements()) {
				String sheetName = (String) keys.nextElement();
				String[][] data = (String[][]) datas.get(sheetName);
				WritableSheet ws = wwb.createSheet(sheetName, sheet + 1);

				for (int row = 0; data != null && row < data.length; row++) {
					for (int col = 0; data[row] != null && col < data[row].length; col++) {
						if (data[row][col] == null)
							continue;
						Label label = new Label(col + 0, row + 0, data[row][col]);
						ws.addCell(label);
					}
				}
			}

			wwb.write();
			wwb.close();
		} catch (Exception e) {
			throw new JetException(e);
		}
	}

	public static void writeExcelData(String file, String sheetName, int x, int y, ArrayList datas) throws JetException {
		try {
			OutputStream os = new FileOutputStream(file);
			WritableWorkbook wwb = Workbook.createWorkbook(os);

			if (sheetName == null || sheetName.trim().length() <= 0)
				sheetName = "Sheet";
			int size = (datas == null) ? 0 : datas.size();
			for (int sheet = 0; sheet < size; sheet++) {
				WritableSheet ws = wwb.createSheet(sheetName + (sheet + 1), sheet);
				String[][] data = (String[][]) datas.get(sheet);
				for (int row = 0; data != null && row < data.length; row++) {
					for (int col = 0; data[row] != null && col < data[row].length; col++) {
						if (data[row][col] == null)
							continue;
						Label label = new Label(col + x, row + y, data[row][col]);
						ws.addCell(label);
					}
				}
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			throw new JetException(e);
		}
	}

	public static void writeTxt(String fileName, ArrayList data) throws JetException {
		try {
			FileWriter file = new FileWriter(fileName);
			PrintWriter writer = new PrintWriter(file);
			Iterator it = data.iterator();
			while (it.hasNext()) {
				String content = (String) it.next();
				writer.print(content);
				writer.println("");
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			throw new JetException(e);
		}
	}

	public static void writeFile(File fileName, String content) throws JetException {
		try {
			FileWriter file = new FileWriter(fileName);
			PrintWriter writer = new PrintWriter(file);
			writer.print(content);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			throw new JetException(e);
		}
	}

	// jar uvf mm.jar com
	public static void adjustJarFile(String dir, String jarFileName, String fileName, String classPath)
			throws JetException {
		try {
			File tempJarFile = File.createTempFile(jarFileName, null, new File(dir));
			jarFileName = dir + jarFileName;
			JarFile jar = new JarFile(jarFileName);
			JarOutputStream tempJar = new JarOutputStream(new FileOutputStream(tempJarFile));
			byte buffer[] = new byte[1024];
			int bytesRead;
			Enumeration entries = jar.entries();

			while (entries.hasMoreElements()) {
				JarEntry entry = (JarEntry) entries.nextElement();
				String name = entry.getName();
				if (name.equals(classPath))
					continue;
				InputStream is = jar.getInputStream(entry);
				tempJar.putNextEntry(entry);
				while ((bytesRead = is.read(buffer)) != -1)
					tempJar.write(buffer, 0, bytesRead);
			}

			FileInputStream fis = new FileInputStream(fileName);
			JarEntry entry = new JarEntry(classPath);
			tempJar.putNextEntry(entry);
			while ((bytesRead = fis.read(buffer)) != -1)
				tempJar.write(buffer, 0, bytesRead);
			fis.close();
			tempJar.close();
			jar.close();
			File origFile = new File(jarFileName);
			origFile.delete();
			tempJarFile.renameTo(origFile);
		} catch (Exception e) {
			throw new JetException(e);
		}
	}
}