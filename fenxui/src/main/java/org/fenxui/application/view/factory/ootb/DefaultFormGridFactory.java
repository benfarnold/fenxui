package org.fenxui.application.view.factory.ootb;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.GridPane;
import org.fenxui.application.view.factory.FormGridFactory;
import org.fenxui.application.view.factory.handler.AnnotationHandler;
import org.fenxui.application.view.factory.handler.FormContext;
import org.fenxui.application.view.factory.handler.NodeContext;
import org.fenxui.application.view.factory.handler.RowContext;

/**
 *
 * @author ArnoldBe
 */
public class DefaultFormGridFactory implements FormGridFactory {
	private final Map<Class, AnnotationHandler> annotationHandlers;

	DefaultFormGridFactory(Map<Class, AnnotationHandler> annotationHandlers) {
		this.annotationHandlers = annotationHandlers;
	}

	@Override
	public GridPane makeForm(Object applicationPage) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		GridPane gridPane = new GridPane();
		int rowIndex = 0;

		FormContext formContext = new FormContext();
		for (Field field : applicationPage.getClass().getDeclaredFields()) {
			NodeContext fieldContext = new NodeContext(field, applicationPage, new RowContext(gridPane, rowIndex), formContext);
			formContext.put(field.getName(), fieldContext);
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
