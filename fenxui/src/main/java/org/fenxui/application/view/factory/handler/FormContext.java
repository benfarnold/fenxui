package org.fenxui.application.view.factory.handler;

import org.fenxui.application.exception.FenxuiInitializationException;

import java.util.HashMap;
import java.util.Map;

public class FormContext {

	private final Map<String, NodeContext> nodesInForm = new HashMap<>();

	public NodeContext get(String refFieldName) {
		return nodesInForm.get(refFieldName);
	}

	public void put(String name, NodeContext fieldContext) {
		nodesInForm.put(name, fieldContext);
	}

	public void postProcessNodes() throws FenxuiInitializationException {
		for (NodeContext context : nodesInForm.values()) {
			context.postProcess();
		}
	}

}
