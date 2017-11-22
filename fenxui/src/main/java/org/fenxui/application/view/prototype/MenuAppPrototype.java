package org.fenxui.application.view.prototype;

import javafx.stage.Stage;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.decorator.SceneDecorator;
import org.fenxui.application.view.decorator.SplitPaneDecorator;
import org.fenxui.application.view.factory.FenxuiFactory;
import org.fenxui.application.view.factory.ootb.AppFactory;
import org.fenxui.application.view.factory.ootb.DefaultAppFactory;

/**
 * Prototype for a page with a fixed left-hand menu linking to stacked content
 * panes on right Only the one content pane will be visible at a time
 */
public interface MenuAppPrototype extends FenxuiPrototype {

	public static FenxuiPrototype newInstance(FenxuiViewModel fenxuiViewModel) {
		return newInstance(fenxuiViewModel, new DefaultAppFactory());
	}

	public static FenxuiPrototype newInstance(FenxuiViewModel fenxuiViewModel, AppFactory appFactory) {
		return (FenxuiConfig fenxuiConfig) -> {
			SceneDecorator sceneDecorator = new SceneDecorator(new SplitPaneDecorator(appFactory));
			return (FenxuiFactory) (Stage stage) -> {
				fenxuiViewModel.setStage(stage);
				return sceneDecorator.decorate(fenxuiViewModel, fenxuiConfig);
			};
		};
	}

}
