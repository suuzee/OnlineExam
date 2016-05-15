/*
 * 创建日期 2005-1-1
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.speedy.base.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.speedy.base.common.JetException;
import com.speedy.base.common.JetLog;

/**
 * @author Administrator
 * 
 *         更改所生成类型注释的模板为 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class SendMail {
	String from = "saleadmin@mingya.com.cn";
	static final String USER = "salesadmin";
	static final String PASSWORD = "ming2009ya";
	ArrayList tos = new ArrayList();
	ArrayList ccs = new ArrayList();

	String title = null;
	String content = null;
	String type = "text/plain;charset=GBK";

	// String mailServer = "mail.mingya.com.cn";
	// String smtpServer = "smtp.mingya.com.cn";
	String mailServer = "210.72.226.193";
	String smtpServer = "210.72.226.193";

	String user = null;
	String name = null;
	String passwd = null;
	ArrayList files = new ArrayList();

	public void setMailServer(String server) {
		this.mailServer = server;
	}

	public void setSmtpServer(String server) {
		this.smtpServer = server;
	}

	public void setUser(String user, String passwd) {
		if (user == null || user.trim().length() <= 0 || passwd == null || passwd.trim().length() <= 0) {
			user = USER;
			passwd = PASSWORD;
		}
		this.user = user;
		this.passwd = passwd;
	}

	public void setFrom(String from, String name) {
		this.from = from;
		this.name = name;
	}

	public void addTo(String to) {
		tos.add(to);
	}

	public void addCC(String cc) {
		ccs.add(cc);
	}

	public void setTitle(String title) {
		// this.title = encodeString(title);
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void addFile(String fileName) {
		files.add(fileName);
	}

	protected void error(String msg, Throwable e) {
		JetLog.error(this, msg, e);
	}

	protected InternetAddress[] getInternetAddress(ArrayList addr) {
		int size = addr.size();
		InternetAddress[] ret = new InternetAddress[size];
		for (int i = 0; i < size; i++) {
			try {
				ret[i] = new InternetAddress((String) addr.get(i));
			} catch (Exception e) {
				error(e.getMessage(), e);
			}
		}
		return ret;
	}

	protected String getFileName(String fileName) {
		int pos = fileName.lastIndexOf("/");
		if (pos >= 0)
			fileName = fileName.substring(pos + 1);
		pos = fileName.lastIndexOf("\\");
		if (pos >= 0)
			fileName = fileName.substring(pos + 1);
		return encodeString(fileName);
	}

	protected String encodeString(String str) {
		try {
			String rs = new String(str.getBytes("GB2312"), "ISO8859-1");
			return rs;
		} catch (Exception e) {
			return null;
		}
	}

	protected MimeMessage getMessage(Session session) throws JetException {
		try {
			MimeMessage message = new MimeMessage(session);

			// 给消息对象设置发件人/收件人/主题/发信时间
			InternetAddress ifrom = new InternetAddress(from,name);
			message.setFrom(ifrom);
			message.setRecipients(Message.RecipientType.TO, getInternetAddress(tos));
			message.setRecipients(Message.RecipientType.CC, getInternetAddress(ccs));
			message.setSubject(title);
			message.setSentDate(new Date());

			// 新建一个MimeMultipart对象用来存放多个BodyPart对象
			Multipart mm = new MimeMultipart();

			// 设置信件文本内容
			BodyPart mdp = new MimeBodyPart();
			mdp.setContent(content, type);
			mm.addBodyPart(mdp);
			// 设置附件
			int size = files.size();
			for (int i = 0; i < size; i++) {
				String fileName = (String) files.get(i);
				mdp = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(fileName);
				DataHandler dh = new DataHandler(fds);
				mdp.setFileName(getFileName(fileName));
				mdp.setDataHandler(dh);
				mm.addBodyPart(mdp);
			}

			message.setContent(mm);
			message.saveChanges();

			return message;
		} catch (Exception e) {
			throw new JetException(e);
		}
	}

	public boolean send() throws JetException {
		int MAX_TIMES = 4;
		for (int i = 0; i < MAX_TIMES; i++) {
			try {
				sendCore();
			} catch (JetException e) {
				if (i == MAX_TIMES - 1) {
					JetLog.error(this, "发送邮件出错：" + this.title, e);
					return false;
				}
				continue;
			}
			return true;
		}
		return false;
	}

	public void sendCore() throws JetException {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.auth", "true");
			Session s = Session.getInstance(props);
			s.setDebug(false);

			MimeMessage message = getMessage(s);
			if (user == null || user.trim().length() <= 0 || passwd == null || passwd.trim().length() <= 0) {
				user = USER;
				passwd = PASSWORD;
			}
			Transport transport = s.getTransport("smtp");
			transport.connect(mailServer, user, passwd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			throw new JetException(e.getMessage(), e);
		}
	}
}
