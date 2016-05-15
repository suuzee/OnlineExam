package com.speedy.base.common;

import java.io.PrintStream;
import java.io.PrintWriter;

public class JetException extends Exception {
	public static final int EXCEPTION_TYPE_ERROR = 0;
	public static final int EXCEPTION_TYPE_HINT = 1;

	protected Throwable jetException = null;
	protected int type = EXCEPTION_TYPE_ERROR;

	private String msg;

	public int getExceptionType() {
		return this.type;
	}

	public int getSuperExceptionType() {
		Throwable parent = this;
		Throwable child;
		while ((child = getNestedException(parent)) != null) {
			if (child instanceof JetException) {
				JetException c = (JetException) child;
				if (c.getMsg() == null)
					this.msg = c.getMessage();
				else
					this.msg = c.getMsg();
				this.type = c.getExceptionType();
			} else if (child instanceof java.lang.reflect.InvocationTargetException) {
				java.lang.reflect.InvocationTargetException c = (java.lang.reflect.InvocationTargetException) child;
				child = c.getTargetException();
			}
			parent = child;
		}
		return type;
	}

	public void setError() {
		type = EXCEPTION_TYPE_ERROR;
	}

	public void setHint() {
		type = EXCEPTION_TYPE_HINT;
	}

	public void setExceptionType(int type) {
		this.type = type;
	}

	public JetException() {
		super();
	}

	public JetException(String message) {
		super(message);
		this.msg = message;
	}

	public JetException(Throwable th) {
		super();
		jetException = th;
	}

	public JetException(String message, Throwable th) {
		super(message);
		jetException = th;
	}

	public Throwable initCauseException(Throwable th) {
		jetException = th;
		return th;
	}

	protected Throwable getNestedException(Throwable parent) {
		if (parent instanceof JetException)
			return ((JetException) parent).jetException;
		else
			return null;
	}

	public String getMessage() {
		String msg = super.getMessage();
		Throwable parent = this;
		Throwable child;
		while ((child = getNestedException(parent)) != null) {
			String cmsg = child.getMessage();
			if (cmsg != null) {
				if (msg != null)
					msg += ": " + cmsg;
				else msg = cmsg;
			}
			parent = child;
		}
		return msg;
	}
/*	public String getMessage() {
		String msg = super.getMessage();
		Throwable parent = this;
		Throwable child;
		while ((child = getNestedException(parent)) != null) {
			String msg2 = child.getMessage();
			if (msg2 != null) {
				if (msg != null)
					msg += ": " + msg2;
				else
					msg = msg2;
			}
			if (child instanceof JetException)
				break;
			parent = child;
		}
		return msg;
	}
*/
	public void printStackTrace() {
		super.printStackTrace();
		Throwable parent = this;
		Throwable child;
		while ((child = getNestedException(parent)) != null) {
			if (child != null) {
				System.err.print("Caused by: ");
				child.printStackTrace();
				if (child instanceof JetException)
					break;
				parent = child;
			}
		}
	}

	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
		Throwable parent = this;
		Throwable child;
		while ((child = getNestedException(parent)) != null) {
			if (child != null) {
				s.print("Caused by: ");
				child.printStackTrace(s);
				if (child instanceof JetException)
					break;
				parent = child;
			}
		}
	}

	public void printStackTrace(PrintWriter w) {
		Throwable parent = this;
		Throwable child = getNestedException(parent);
		if (child==null)
			super.printStackTrace(w);
		else {
			w.print("Caused by: ");
			w.println(child.getClass().getName());
			child.printStackTrace(w);
		}
	}
/*	public void printStackTrace(PrintWriter w) {
		super.printStackTrace(w);
		Throwable parent = this;
		Throwable child;
		while ((child = getNestedException(parent)) != null) {
			if (child != null) {
				w.print("Caused by: ");
				child.printStackTrace(w);
				if (child instanceof JetException)
					break;
				parent = child;
			}
		}
	}
*/
	public Throwable getCauseException() {
		return jetException;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String string) {
		msg = string;
	}
}