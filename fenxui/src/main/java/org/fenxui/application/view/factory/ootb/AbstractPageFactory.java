package org.fenxui.application.view.factory.ootb;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.data.DataService;
import org.fenxui.application.data.bind.BoundReference;
import org.fenxui.application.data.bind.BoundReferenceMap;
import org.fenxui.application.data.nitrite.IdAware;
import org.fenxui.application.service.ServiceLookup;
import org.fenxui.application.view.bind.ContentPane;
import org.fenxui.application.view.factory.FactoryInitContext;
import org.fenxui.application.view.factory.FormActionFactory;
import org.fenxui.application.view.factory.FormGridFactory;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.PageContext;

/**
 * Lays out page as a grid within a vbox vbox: title, grid grid: Row 1....n form
 * elements 1..n (A form element is typically a label and a field, but may also
 * include a button that populates the field) Row n+1 form action buttons row
 */
public class AbstractPageFactory implements PageFactory {
	private final FormGridFactory formGridFactory;
	private final FormActionFactory formActionFactory;
	private final FactoryInitContext factoryInitContext;
	private final Map<Class, PageAnnotationHandler> pageAnnotationHandlers;

	public AbstractPageFactory(FormGridFactory formRowFactory, FormActionFactory formActionFactory, FactoryInitContext factoryInitContext) {
		this.formGridFactory = formRowFactory;
		this.formActionFactory = formActionFactory;
		this.factoryInitContext = factoryInitContext;
		this.pageAnnotationHandlers = factoryInitContext.getPageAnnotationHandlers();
	}

	@Override
	public ContentPane makePage(Object applicationPage, FenxuiConfig fenxuiConfig) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		ContentPane contentPane = new ContentPane();
		contentPane.setAlignment(fenxuiConfig.getAlignent());

		PageContext pageContext = new PageContext(applicationPage);
		for (Annotation annotation : applicationPage.getClass().getAnnotations()) {
			PageAnnotationHandler handler = pageAnnotationHandlers.get(annotation.annotationType());
			if (handler != null) {
				handler.handle(pageContext, annotation);
			}
		}
		contentPane.getChildren().add(pageContext.getTitle());
		GridPane gridPane = formGridFactory.makeForm(applicationPage, fenxuiConfig);
		contentPane.getChildren().add(gridPane);

		HBox hbBtn = formActionFactory.makeFormActionRow(applicationPage);
		contentPane.getChildren().add(hbBtn);

		contentPane.getChildren().add(1, new Label());//space after title
		contentPane.getChildren().add(4, new Label());//space above form submit button
		contentPane.setSpacing(10);

		if (pageContext.isAutoLoad()) {
			DataService dataService = (DataService) ServiceLookup.INSTANCE.getService(DataService.NAME);
			if (dataService != null) {
				BoundReference br = BoundReferenceMap.INSTANCE.get(applicationPage);
				if (br != null) {
					Iterable<IdAware> found = dataService.getAll(br.getBoundDbClass());
					if (found != null) {
						br.update(found.iterator().next());
					}
				}

			}
		}


		return contentPane;
	}

	@Override
	public FactoryInitContext getFactoryInitContext() {
		return factoryInitContext;
	}
}
