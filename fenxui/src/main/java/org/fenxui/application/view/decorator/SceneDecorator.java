package org.fenxui.application.view.decorator;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;

/**
 * Scene layout
 */
public class SceneDecorator extends AbstractDecorator<FenxuiDecorator> implements ComponentDecorator<Scene, FenxuiViewModel> {

	public SceneDecorator(WindowDecorator windowDecorator) {
		super(windowDecorator);
	}

	@Override
	public Scene decorate(FenxuiViewModel fenxuiViewModel, FenxuiConfig fenxuiConfig) {
		Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
		double width = bounds.getWidth() / 2.5;
		double height = bounds.getHeight() / 1.35;
		FenxuiDecorator decore = decorator.decorate(fenxuiViewModel, fenxuiConfig);
		Scene scene = new Scene(decore, width, height);

		scene.getStylesheets().addAll(fenxuiConfig.getStylesheets());
		return scene;
	}

}
