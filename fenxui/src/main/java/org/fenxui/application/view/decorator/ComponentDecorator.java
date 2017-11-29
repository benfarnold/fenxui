package org.fenxui.application.view.decorator;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;

public interface ComponentDecorator<T, U> {

	T decorate(U u, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException;
}
