package com.speedy.base.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.speedy.base.common.JetException;

public class XMLReader {
	public static Element getRootElement(String file) throws JetException {
		try {
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(new FileInputStream(file));
			Element root = doc.getRootElement();
			return root;
		} catch (Exception e) {
			throw new JetException(e);
		}
	}

	public static Element getRootElement(InputStream is) throws JetException {
		try {
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(is);
			Element root = doc.getRootElement();
			return root;
		} catch (Exception e) {
			throw new JetException(e);
		}
	}

	public static Element getRootElementFromString(String xmlStr) throws JetException {
		try {
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(new StringReader(xmlStr));
			Element root = doc.getRootElement();
			return root;
		} catch (Exception e) {
			throw new JetException(e);
		}
	}
}
