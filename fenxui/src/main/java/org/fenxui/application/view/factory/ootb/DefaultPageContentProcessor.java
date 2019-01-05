package org.fenxui.application.view.factory.ootb;

import javafx.scene.layout.Region;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.ContentPane;
import org.fenxui.application.view.components.FormContainer;
import org.fenxui.application.view.components.NamedHideable;
import org.fenxui.application.view.components.menu.NavigableMenu;
import org.fenxui.application.view.components.menu.SideMenu;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.handler.AnnotationHandler;
import org.fenxui.application.view.factory.handler.NodeContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javafx.beans.property.StringProperty;
import org.fenxui.application.view.factory.PageContentProcessor;
import org.fenxui.application.view.factory.handler.app.Orientation;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.application.view.factory.handler.util.ReflectiveFieldRetriever;

public class DefaultPageContentProcessor implements PageContentProcessor {
	private final Map<Class, AnnotationHandler> annotationHandlers;
	private PageFactory pageFactory;

	public DefaultPageContentProcessor(Map<Class, AnnotationHandler> annotationHandlers) {
		this.annotationHandlers = annotationHandlers;
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
			NodeContext fieldContext = new NodeContext(field, applicationPage, pageContext);
			if (fieldInstance instanceof StringProperty) {
				fieldContext.getActiveFieldOption().setValue((StringProperty) fieldInstance);
				removeFieldContext = false;//only form fields should have FieldOptions
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
//		formContext.postProcessNodes();
//		return gridPane;
	}

}
