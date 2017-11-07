package org.fenxui.application.view.factory;

import javafx.scene.Scene;
import org.fenxui.application.view.FenxuiViewModel;

public interface FenxuiFactory {

	Scene makeScene(FenxuiViewModel fenxuiViewModel);
}
