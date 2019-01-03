package org.fenxui.application.view.prototype;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.decorator.BorderPaneDecorator;
import org.fenxui.application.view.decorator.JFXWindowDressingDecorator;
import org.fenxui.application.view.decorator.SceneDecorator;
import org.fenxui.application.view.factory.FenxuiFactory;
import org.fenxui.application.view.factory.ootb.AppFactory;
import org.fenxui.application.view.factory.ootb.DefaultAppFactory;

/**
 * Prototype for a page with a fixed left-hand menu linking to stacked content
 * panes on right Only the one content pane will be visible at a time
 */
public interface MenuAppPrototype extends FenxuiPrototype {
	static FenxuiPrototype newInstance(FenxuiViewModel fenxuiViewModel, Runnable onCloseAction) {
		return newInstance(fenxuiViewModel, new DefaultAppFactory(onCloseAction));
	}

	static FenxuiPrototype newInstance(FenxuiViewModel fenxuiViewModel, AppFactory appFactory) {
		return (FenxuiConfig fenxuiConfig) -> {
			appFactory.setViewModel(fenxuiViewModel);
			SceneDecorator sceneDecorator = new SceneDecorator(new JFXWindowDressingDecorator(new BorderPaneDecorator(appFactory)));
			return (FenxuiFactory) (Stage stage) -> {
				fenxuiViewModel.setStage(stage);
				return sceneDecorator.decorate(fenxuiViewModel, fenxuiConfig);
			};
		};
	}

}
