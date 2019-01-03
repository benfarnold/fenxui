package org.fenxui.application.view.factory.ootb;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.ContentPane;
import org.fenxui.application.view.factory.FactoryInitContext;

public interface PageFactory {

	ContentPane makePage(Object applicationPage, FenxuiConfig fenxuiConfig, AppConstruction appConstruction) throws FenxuiInitializationException;

	FactoryInitContext getFactoryInitContext();
}
