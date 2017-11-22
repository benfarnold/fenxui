package org.fenxui.application.view.factory.ootb;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.bind.ContentPane;
import org.fenxui.application.view.factory.handler.app.AppAnnotationHandler;

public abstract class AbstractAppFactory {

	private final PageFactory pageFactory;
	private final Map<Class, AppAnnotationHandler> appAnnotationHandlers;

	public AbstractAppFactory(PageFactory pageFactory) {
		this.pageFactory = pageFactory;
		this.appAnnotationHandlers = pageFactory.getFactoryInitContext().getAppAnnotationHandlers();
	}

	public AppConstruction makeApp(FenxuiViewModel viewModel, FenxuiConfig fenxuiConfig) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		AppConstruction appConstruction = new AppConstruction(viewModel);

		int i = 0;
		for (Field field : viewModel.getClass().getDeclaredFields()) {
			Object applicationPage = FieldUtils.readField(field, viewModel, true);
			ContentPane contentPane = pageFactory.makePage(applicationPage, fenxuiConfig);
			contentPane.setVisible(i == 0);
			appConstruction.addContentPane(contentPane);

			for (Annotation annotation : field.getAnnotations()) {
				AppAnnotationHandler handler = appAnnotationHandlers.get(annotation.annotationType());
				if (handler != null) {
					handler.handle(appConstruction, annotation);
				}
			}
			i++;
		}

		return appConstruction;
	}

	public PageFactory getPageFactory() {
		return pageFactory;
	}

}
