/*
 * 创建日期 2006-6-2
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.speedy.base.util;

/**
 * @author xzm
 * 
 *         更改所生成类型注释的模板为 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class ProgressLog {
	PrintThread print = null;
	Thread thread = null;

	public void start() {
		if (print == null)
			print = new PrintThread();
		print.run = true;
		thread = new Thread(print);
		thread.start();
	}

	public void start(String info, long sp) {
		if (print == null)
			print = new PrintThread(info, sp);
		print.run = true;
		thread = new Thread(print);
		thread.start();
	}

	public void stop() {
		if (print == null)
			return;
		print.run = false;
		print = null;
	}
}
class PrintThread implements Runnable {
	String info = ".";
	long time = 50;
	boolean run = true;

	public PrintThread() {
	}

	public PrintThread(String info) {
		this.info = info;
	}

	public PrintThread(String info, long time) {
		this.info = info;
		this.time = time;
	}

	public void run() {
		try {
			while (run) {
				System.out.print(info);
				Thread.sleep(time);
			}
		} catch (InterruptedException interruptedexception) {
			return;
		}
	}
}