package org.fenxui.application.view.factory.handler.app;

import java.lang.annotation.Annotation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.factory.handler.PostProcessor;
import org.fenxui.application.view.factory.ootb.AppConstruction;

/**
 * Handle MenuItem annotations
 */
public class MenuItemAnnotationHandler implements AppAnnotationHandler {
	@Override
	public void handle(AppConstruction appContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException {
		MenuItem menuItem = (MenuItem) annotation;
		Label label = new Label(menuItem.value());
		label.getStyleClass().add(menuItem.cssClass());
		appContext.addPostProcessor(new PostProcessor() {
			@Override
			public void postProcess(Node node) throws FenxuiInitializationException {
				label.setOnMouseClicked((av) -> {
					appContext.getContentPanes().forEach(pane -> pane.setVisible(false));
					node.setVisible(true);
				});
			}
		});
		appContext.addMenuItem(label);
	}

}
