package org.fenxui.application.view.factory.ootb;

import javafx.beans.property.Property;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.api.factory.FieldFactory;
import org.fenxui.api.factory.ValidatorFactory;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.components.NamedHideable;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.PageContentProcessor;
import org.fenxui.application.view.factory.handler.AnnotationHandler;
import org.fenxui.application.view.factory.handler.NodeContext;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.application.view.factory.handler.util.ReflectiveFieldRetriever;
import org.fenxui.core.exception.FenxuiInitializationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class DefaultPageContentProcessor implements PageContentProcessor {
	private final Map<Class, AnnotationHandler> annotationHandlers;
	private final Map<String, FieldFactory> fieldFactories;
	private final Map<String, ValidatorFactory> validatorFactories;

	private PageFactory pageFactory;

	public DefaultPageContentProcessor(Map<Class, AnnotationHandler> annotationHandlers, Map<String, FieldFactory> fieldFactories, Map<String, ValidatorFactory> validatorFactories) {
		this.annotationHandlers = annotationHandlers;
		this.fieldFactories = fieldFactories;
		this.validatorFactories = validatorFactories;
	}

	@Override
	public void setPageFactory(PageFactory pageFactory) {
		this.pageFactory = pageFactory;
	}

	@Override
	public void processPageContent(PageContext pageContext, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {

		Object applicationPage = pageContext.getApplicationPage();
		for (Field field : applicationPage.getClass().getDeclaredFields()) {
			Object fieldInstance = new ReflectiveFieldRetriever(field).getFieldValue(applicationPage);
			boolean removeFieldContext = true;
			NodeContext fieldContext = new NodeContext(field, applicationPage, pageContext, fieldFactories, validatorFactories);
			if (fieldInstance instanceof Property) {
				removeFieldContext = false;//only factory fields should have FieldOptions
				fieldContext.getActiveFieldOption().setValue((Property) fieldInstance);
			} else if (field.getAnnotationsByType(MenuItem.class).length == 1) {//process page links
				NamedHideable contentPane = (NamedHideable) pageFactory.makePage(fieldInstance, fenxuiConfig, pageContext);
				pageContext.addContentPane(contentPane);
			}

			for (Annotation annotation : field.getAnnotations()) {
				AnnotationHandler annotationHandler = annotationHandlers.get(annotation.annotationType());
				if (annotationHandler != null) {
					annotationHandler.handle(fieldContext, annotation);
				}
			}

			if (removeFieldContext) {
				List<FieldOption> fieldOptionList = pageContext.getFieldOptions();
				fieldOptionList.remove(fieldOptionList.size()-1);
			}
		}
	}

}
