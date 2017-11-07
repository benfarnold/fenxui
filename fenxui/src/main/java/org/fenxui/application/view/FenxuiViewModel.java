package org.fenxui.application.view;

import javafx.stage.Stage;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.factory.FenxuiFactory;

public abstract class FenxuiViewModel {
	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public abstract FenxuiFactory getFenxuiFactory(FenxuiConfig fenxuiConfig);

}
