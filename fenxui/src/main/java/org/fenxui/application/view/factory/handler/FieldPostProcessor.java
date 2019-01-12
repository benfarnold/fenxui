package org.fenxui.application.view.factory.handler;

import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;

public interface FieldPostProcessor {
	void postProcess(FieldOption fieldOption) throws FenxuiInitializationException;
	String getFieldName();
}
