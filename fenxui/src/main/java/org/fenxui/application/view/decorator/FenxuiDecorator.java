package org.fenxui.application.view.decorator;

import com.jfoenix.controls.JFXDecorator;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * Wraps the Application with common close buttons, title bar etc.
 */
public class FenxuiDecorator extends JFXDecorator {

	public FenxuiDecorator(Stage stage, Node node) {
		super(stage, node);
	}

}
