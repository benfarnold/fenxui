package org.fenxui.application.view.prototype;

import javafx.stage.Stage;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.decorator.ContentPaneDecorator;
import org.fenxui.application.view.decorator.SceneDecorator;
import org.fenxui.application.view.decorator.SplitPaneDecorator;
import org.fenxui.application.view.decorator.WindowDecorator;
import org.fenxui.application.view.factory.FenxuiFactory;
import org.fenxui.application.view.factory.ootb.DefaultPageFactory;

/**
 * Prototype for a page with a fixed left-hand menu linking to stacked content
 * panes on right Only the one content pane will be visible at a time
 */
public interface MenuAppPrototype extends FenxuiPrototype {

	public static FenxuiPrototype newInstance(FenxuiViewModel fenxuiViewModel) {
		return (FenxuiConfig fenxuiConfig) -> {
			SceneDecorator sceneDecorator = new SceneDecorator(new WindowDecorator(new SplitPaneDecorator(new ContentPaneDecorator(new DefaultPageFactory()))));
			return (FenxuiFactory) (Stage stage) -> {
				fenxuiViewModel.setStage(stage);
				return sceneDecorator.decorate(fenxuiViewModel, fenxuiConfig);
			};
		};
	}

}
