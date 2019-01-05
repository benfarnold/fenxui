package org.fenxui.application.view.decorator;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.factory.ootb.AppFactory;

public interface ComponentDecorator<T> {

	T decorate(FenxuiConfig fenxuiConfig) throws FenxuiInitializationException;
	AppFactory getAppFactory();
}
