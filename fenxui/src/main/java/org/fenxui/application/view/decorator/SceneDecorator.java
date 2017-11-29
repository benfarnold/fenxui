package org.fenxui.application.view.decorator;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.FenxuiViewModel;

/**
 * Scene layout
 */
public class SceneDecorator extends AbstractDecorator<Region> implements ComponentDecorator<Scene, FenxuiViewModel> {

	public SceneDecorator(ComponentDecorator decorator) {
		super(decorator);
	}

	@Override
	public Scene decorate(FenxuiViewModel fenxuiViewModel, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {
		Region region = decorator.decorate(fenxuiViewModel, fenxuiConfig);
		Scene scene = new Scene(region, fenxuiConfig.getInitialSceneWidth(), fenxuiConfig.getInitialSceneHeight());

		scene.getStylesheets().addAll(fenxuiConfig.getStylesheets());
		return scene;
	}

}
