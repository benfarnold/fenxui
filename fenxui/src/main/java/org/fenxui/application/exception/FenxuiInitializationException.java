package org.fenxui.application.exception;

public class FenxuiInitializationException extends Exception {

	public FenxuiInitializationException(Throwable throwable) {
		super(throwable);
	}

	public FenxuiInitializationException(String msg) {
		super(msg);
	}
}
