package org.fenxui.application.view.factory.ootb;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.factory.handler.AnnotationHandler;
import org.fenxui.application.view.factory.handler.NodeContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import javafx.beans.property.StringProperty;
import org.fenxui.application.view.factory.PageContentProcessor;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.application.view.factory.handler.util.ReflectiveFieldRetriever;

public class DefaultPageContentProcessor implements PageContentProcessor {
	private final Map<Class, AnnotationHandler> annotationHandlers;

	public DefaultPageContentProcessor(Map<Class, AnnotationHandler> annotationHandlers) {
		this.annotationHandlers = annotationHandlers;
	}

	@Override
	public void processPageContent(PageContext pageContext, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {

		Object applicationPage = pageContext.getApplicationPage();
		for (Field field : applicationPage.getClass().getDeclaredFields()) {
			Object fieldInstance = new ReflectiveFieldRetriever(field).getFieldValue(applicationPage);
			if (fieldInstance instanceof StringProperty) {//for now just supporting StringProperty
				NodeContext fieldContext = new NodeContext(field, applicationPage, pageContext);
				fieldContext.getActiveFieldOption().setValue((StringProperty) fieldInstance);
				for (Annotation annotation : field.getAnnotations()) {
					AnnotationHandler annotationHandler = annotationHandlers.get(annotation.annotationType());
					if (annotationHandler != null) {
						annotationHandler.handle(fieldContext, annotation);
					}
				}
			}
		}
//		formContext.postProcessNodes();
//		return gridPane;
	}

}
