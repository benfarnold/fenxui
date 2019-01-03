package org.fenxui.application.view.factory.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.handler.page.PageContext;

public class NodeContext {
	private final List<PostProcessor> postProcessors = new ArrayList<>();
	private Node node;
	private final Field field;
	private final Object source;
	private final FieldOption activeFieldOption;
	private final PageContext pageContext;

	public NodeContext(Field field, Object source, PageContext pageContext) {
		this.field = field;
		this.source = source;
		this.activeFieldOption = new FieldOption(field.getName());
		this.pageContext = pageContext;
		pageContext.addFieldOption(activeFieldOption);
	}

	public FieldOption getActiveFieldOption() {
		return activeFieldOption;
	}
	
	public void setNode(Node node) {
		this.node = node;
	}

	public void addPostProcessor(PostProcessor postProcessor) {
		this.postProcessors.add(postProcessor);
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

}
