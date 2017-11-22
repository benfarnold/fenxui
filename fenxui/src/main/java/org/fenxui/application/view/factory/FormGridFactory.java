package org.fenxui.application.view.factory;

import java.lang.reflect.InvocationTargetException;
import javafx.scene.layout.GridPane;
import org.fenxui.application.config.FenxuiConfig;

/**
 * Makes form region of the page
 */
public interface FormGridFactory {

	GridPane makeForm(Object applicationPage, FenxuiConfig fenxuiConfig) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
