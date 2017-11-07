package org.fenxui.application.view.factory;

import java.lang.reflect.InvocationTargetException;
import javafx.scene.layout.GridPane;

/**
 * Makes form region of the page
 */
public interface FormGridFactory {

	GridPane makeForm(Object applicationPage) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
