package org.fenxui.application.view.factory.handler.node;

import javafx.scene.Node;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.factory.handler.NodeContext;

public class FormNodeHandler {
	private final Node node;

	public FormNodeHandler(Node node) {
		this.node = node;
	}

	public void handle(NodeContext fieldContext) throws FenxuiInitializationException {
		fieldContext.setNode(node);
		fieldContext.getRowContext().setFormField(node);
	}
}
