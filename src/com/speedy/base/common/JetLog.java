package com.speedy.base.common;

import java.util.Hashtable;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * »’÷æ¿‡
 * 
 */
public class JetLog {
	private static Hashtable hashTable = new Hashtable();
	private static Logger logger;

	public static void debug(Object object, String message) {
		log(Level.DEBUG, object, message, null);
	}

	public static void debug(Object object, String message, Throwable t) {
		log(Level.DEBUG, object, message, t);
	}

	public static void info(Object object, String message) {
		log(Level.INFO, object, message, null);
	}

	public static void info(Object object, String message, Throwable t) {
		log(Level.INFO, object, message, t);
	}

	public static void error(Object object, String message) {
		log(Level.ERROR, object, message, null);
	}

	public static void error(Object object, String message, Throwable t) {
		log(Level.ERROR, object, message, t);
	}

	private static void log(Level level, Object object, String message, Throwable t) {
		if (object == null) {
			object = JetLog.class;
		}
		if (!hashTable.containsKey(object)) {
			String className = object.getClass().getName();
			logger = LogManager.getLogger(className);
			hashTable.put(object, logger);
		} else {
			logger = (Logger) hashTable.get(object);
		}
		switch (level.toInt()) {
		case Level.ERROR_INT:
			if (t != null) {
				logger.error(message, t);
			} else {
				logger.error(message);
			}
			break;
		case Level.DEBUG_INT:
			if (logger.isDebugEnabled()) {
				if (t != null) {
					logger.debug(message, t);
				} else {
					logger.debug(message);
				}
			}
			break;
		case Level.INFO_INT:
			if (logger.isInfoEnabled()) {
				if (t != null) {
					logger.info(message, t);
				} else {
					logger.info(message);
				}
			}
			break;
		default:
			break;
		}
	}

}
