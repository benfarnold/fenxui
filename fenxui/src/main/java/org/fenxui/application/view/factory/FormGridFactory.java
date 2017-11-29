package org.fenxui.application.view.factory;

import javafx.scene.layout.GridPane;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;

/**
 * Makes form region of the page
 */
public interface FormGridFactory {

	GridPane makeForm(Object applicationPage, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException;
}
