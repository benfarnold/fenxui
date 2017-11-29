package org.fenxui.application.view.factory.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import org.fenxui.application.exception.FenxuiInitializationException;

public class NodeContext {
	private final List<PostProcessor> postProcessors = new ArrayList<>();
	private Node node;
	private final Field field;
	private final Object source;
	private final RowContext rowContext;
	private final FormContext formContext;

	public NodeContext(Field field, Object source, RowContext rowContext, FormContext formContext) {
		this.field = field;
		this.source = source;
		this.rowContext = rowContext;
		this.formContext = formContext;
	}

	public RowContext getRowContext() {
		return rowContext;
	}

	public FormContext getFormContext() {
		return formContext;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public void addPostProcessor(PostProcessor postProcessor) {
		this.postProcessors.add(postProcessor);
	}

	public void postProcess() throws FenxuiInitializationException {
		for (PostProcessor postProcessor : postProcessors) {
			postProcessor.postProcess(node);
		}
		rowContext.postProcess();
	}

	public Node getNode() {
		return node;
	}

	public Field getField() {
		return field;
	}

	public Object getSource() {
		return source;
	}

}
