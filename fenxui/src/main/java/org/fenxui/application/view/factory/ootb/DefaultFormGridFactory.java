package org.fenxui.application.view.factory.ootb;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.factory.FormGridFactory;
import org.fenxui.application.view.factory.handler.AnnotationHandler;
import org.fenxui.application.view.factory.handler.FormContext;
import org.fenxui.application.view.factory.handler.NodeContext;
import org.fenxui.application.view.factory.handler.RowContext;
import org.fenxui.application.view.factory.handler.node.FormNodeHandler;
import org.fenxui.application.view.factory.handler.util.ReflectiveFieldRetriever;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public class DefaultFormGridFactory implements FormGridFactory {
	private final Map<Class, AnnotationHandler> annotationHandlers;

	public DefaultFormGridFactory(Map<Class, AnnotationHandler> annotationHandlers) {
		this.annotationHandlers = annotationHandlers;
	}

	@Override
	public GridPane makeForm(Object applicationPage, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {
		GridPane gridPane = new GridPane();
		int rowIndex = 0;

		FormContext formContext = new FormContext();
		for (Field field : applicationPage.getClass().getDeclaredFields()) {
			NodeContext fieldContext = new NodeContext(field, applicationPage, new RowContext(gridPane, rowIndex), formContext);
			formContext.put(field.getName(), fieldContext);
			Object fieldInstance = new ReflectiveFieldRetriever(field)
					.getFieldValue(applicationPage);
			if (fieldInstance instanceof Node) {
				new FormNodeHandler((Node) fieldInstance).handle(fieldContext);
			}
			for (Annotation annotation : field.getAnnotations()) {
				AnnotationHandler annotationHandler = annotationHandlers.get(annotation.annotationType());
				if (annotationHandler != null) {
					annotationHandler.handle(fieldContext, annotation);
				}
			}
			if (fieldContext.getNode() != null) {
				rowIndex++;
			}

		}
		formContext.postProcessNodes();
		return gridPane;
	}

}
