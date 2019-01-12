package org.fenxui.application.view.factory.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.application.view.factory.ootb.form.FieldFactory;

public class NodeContext {
	private Node node;
	private final Field field;
	private final Object source;
	private final FieldOption activeFieldOption;
	private final PageContext pageContext;
	private final Map<String, FieldFactory> fieldFactories;

	public NodeContext(Field field, Object source, PageContext pageContext, Map<String, FieldFactory> fieldFactories) {
		this.field = field;
		this.source = source;
		this.activeFieldOption = new FieldOption(field.getName());
		this.pageContext = pageContext;
		this.fieldFactories = fieldFactories;
		pageContext.addFieldOption(activeFieldOption);
	}

	public FieldOption getActiveFieldOption() {
		return activeFieldOption;
	}
	
	public void setNode(Node node) {
		this.node = node;
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

	public PageContext getPageContext() {
		return pageContext;
	}

	public FieldFactory getFieldFactory(String name) throws FenxuiInitializationException {
		FieldFactory fieldFactory = fieldFactories.get(name);
		if (fieldFactory == null) {
			throw new FenxuiInitializationException("Unknown field type: " + name);
		}
		return fieldFactory;
	}

}
