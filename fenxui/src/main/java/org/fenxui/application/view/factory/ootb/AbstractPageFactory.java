package org.fenxui.application.view.factory.ootb;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.ContentPane;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.FactoryInitContext;
import org.fenxui.application.view.factory.FormActionFactory;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.application.view.factory.PageContentProcessor;

/**
 * Lays out page as a grid within a vbox vbox: title, grid grid: Row 1....n form
 * elements 1..n (A form element is typically a label and a field, but may also
 * include a button that populates the field) Row n+1 form action buttons row
 */
public class AbstractPageFactory implements PageFactory {

	private final PageContentProcessor formGridFactory;
	private final FormActionFactory formActionFactory;
	private final FactoryInitContext factoryInitContext;
	private final Map<Class, PageAnnotationHandler> pageAnnotationHandlers;

	public AbstractPageFactory(PageContentProcessor formRowFactory, FormActionFactory formActionFactory, FactoryInitContext factoryInitContext) {
		this.formGridFactory = formRowFactory;
		this.formActionFactory = formActionFactory;
		this.factoryInitContext = factoryInitContext;
		this.pageAnnotationHandlers = factoryInitContext.getPageAnnotationHandlers();
	}

	@Override
	public ContentPane makePage(Object applicationPage, FenxuiConfig fenxuiConfig, AppConstruction appConstruction) throws FenxuiInitializationException {
		ContentPane contentPane = new ContentPane();
//		contentPane.setAlignment(fenxuiConfig.getAlignent());

		PageContext pageContext = new PageContext(applicationPage, appConstruction);
		for (Annotation annotation : applicationPage.getClass().getAnnotations()) {
			PageAnnotationHandler handler = pageAnnotationHandlers.get(annotation.annotationType());
			if (handler != null) {
				handler.handle(pageContext, annotation);
			}
		}
		contentPane.getStyleClass().setAll(pageContext.getPageCss());
		
//		contentPane.getChildren().add(pageContext.getTitle());
		
		formGridFactory.processPageContent(pageContext, fenxuiConfig);
		
		List<FieldOption> fieldOptions = pageContext.getFieldOptions();
		contentPane.addFields(fieldOptions);
//		contentPane.getChildren().add(gridPane);

//		HBox hbBtn = formActionFactory.makeFormActionRow(applicationPage);
//		contentPane.getChildren().add(hbBtn);

//		contentPane.getChildren().add(1, new Label());//space after title
//		contentPane.getChildren().add(4, new Label());//space above form submit button
//		contentPane.setSpacing(10);
		return contentPane;
	}

	@Override
	public FactoryInitContext getFactoryInitContext() {
		return factoryInitContext;
	}
}
