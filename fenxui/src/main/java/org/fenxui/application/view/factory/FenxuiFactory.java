package org.fenxui.application.view.factory;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fenxui.application.exception.FenxuiInitializationException;

public interface FenxuiFactory {

	Scene makeScene(Stage primaryStage) throws FenxuiInitializationException;
}
