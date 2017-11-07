package org.fenxui.application.view.decorator;

import org.fenxui.application.config.FenxuiConfig;

public interface ComponentDecorator<T, U> {

	T decorate(U u, FenxuiConfig fenxuiConfig);
}
