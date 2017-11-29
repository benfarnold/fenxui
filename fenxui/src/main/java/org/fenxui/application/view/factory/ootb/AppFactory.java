package org.fenxui.application.view.factory.ootb;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.FenxuiViewModel;

public interface AppFactory {

	AppConstruction makeApp(FenxuiViewModel viewModel, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException;

	PageFactory getPageFactory();
}
