package org.fenxui.application.view.factory.ootb;

import java.lang.reflect.InvocationTargetException;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.bind.ContentPane;
import org.fenxui.application.view.factory.FactoryInitContext;

public interface PageFactory {

	ContentPane makePage(Object applicationPage, FenxuiConfig fenxuiConfig) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
	FactoryInitContext getFactoryInitContext();
}
