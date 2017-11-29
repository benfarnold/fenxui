package org.fenxui.application.view.factory.handler;

import javafx.scene.Node;
import org.fenxui.application.exception.FenxuiInitializationException;

@FunctionalInterface
public interface PostProcessor {

	void postProcess(Node node) throws FenxuiInitializationException;
}
