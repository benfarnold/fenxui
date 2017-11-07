package org.fenxui.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.factory.FenxuiFactory;

/**
 * Main class. Extend me.
 */
public abstract class FenxuiApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FenxuiViewModel viewModel = getFenxuiViewModel();
		viewModel.setStage(primaryStage);
		FenxuiFactory fenxuiFactory = viewModel.getFenxuiFactory(getFenxuiConfig());
		Scene scene = fenxuiFactory.makeScene(viewModel);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public abstract FenxuiConfig getFenxuiConfig();

	public abstract FenxuiViewModel getFenxuiViewModel();
}
