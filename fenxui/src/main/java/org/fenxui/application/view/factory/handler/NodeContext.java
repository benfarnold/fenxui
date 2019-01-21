package org.fenxui.application.view.factory.handler;

import javafx.scene.Node;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.handler.action.MethodOption;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.application.view.factory.ootb.form.FieldFactory;
import org.fenxui.application.view.factory.ootb.form.action.ActionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class NodeContext {
	private Node node;
	private final Object source;
	private FieldOption activeFieldOption;
	private MethodOption activeMethodOption;
	private final PageContext pageContext;
	private Map<String, FieldFactory> fieldFactories;
	private Map<String, ActionFactory> actionFactories;

	public NodeContext(Field field, Object source, PageContext pageContext, Map<String, FieldFactory> fieldFactories) {
		this.source = source;
		this.activeFieldOption = new FieldOption(field.getName());
		this.pageContext = pageContext;
		this.fieldFactories = fieldFactories;
		pageContext.addFieldOption(activeFieldOption);
	}

	public NodeContext(Method method, Object source, PageContext pageContext, Map<String, ActionFactory> actionFactories) {
		this.source = source;
		this.activeMethodOption = new MethodOption(method, source);
		this.pageContext = pageContext;
		this.actionFactories = actionFactories;
		pageContext.addMethodOption(activeMethodOption);
	}

	public FieldOption getActiveFieldOption() {
		return activeFieldOption;
	}

	public MethodOption getActiveMethodOption() {
		return activeMethodOption;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
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

	public ActionFactory getActionFactory(String name) throws FenxuiInitializationException {
		ActionFactory actionFactory = actionFactories.get(name);
		if (actionFactory == null) {
			throw new FenxuiInitializationException("Unknown field type: " + name);
		}
		return actionFactory;
	}

}
