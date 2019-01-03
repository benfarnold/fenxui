package org.fenxui.application.view.factory;

import java.util.HashMap;
import java.util.Map;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.CheckBoxValue;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.Validator;
import org.fenxui.annotation.Validators;
import org.fenxui.annotation.ValueProviderValue;
import org.fenxui.annotation.ValueProviderValues;
import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.view.factory.handler.AnnotationHandler;
import org.fenxui.application.view.factory.handler.CheckBoxValueAnnotationHandler;
import org.fenxui.application.view.factory.handler.FormFieldAnnotationHandler;
import org.fenxui.application.view.factory.handler.ValidatorAnnotationHandler;
import org.fenxui.application.view.factory.handler.ValidatorsAnnotationHandler;
import org.fenxui.application.view.factory.handler.ValueProviderValueAnnotationHandler;
import org.fenxui.application.view.factory.handler.ValueProviderValuesAnnotationHandler;
import org.fenxui.application.view.factory.handler.app.AppAnnotationHandler;
import org.fenxui.application.view.factory.handler.app.MenuAnnotationHandler;
import org.fenxui.application.view.factory.handler.app.MenuItemAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.AppPageAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;

/**
 * All annotations to be handled and their handlers Anything not contained here
 * will not be processed.
 */
public class FactoryInitContext {

	private Map<Class, AnnotationHandler> formFieldAnnotationHandlers = FormFactoryPrototype.getDefaultHandlers();
	private Map<Class, AppAnnotationHandler> appAnnotationHandlers = AppFactoryPrototype.getDefaultHandlers();
	private Map<Class, PageAnnotationHandler> pageAnnotationHandlers = PageFactoryPrototype.getDefaultHandlers();

	public Map<Class, AnnotationHandler> getFormFieldAnnotationHandlers() {
		return formFieldAnnotationHandlers;
	}

	public Map<Class, AppAnnotationHandler> getAppAnnotationHandlers() {
		return appAnnotationHandlers;
	}

	public Map<Class, PageAnnotationHandler> getPageAnnotationHandlers() {
		return pageAnnotationHandlers;
	}

	public void setFormFieldAnnotationHandlers(Map<Class, AnnotationHandler> formFieldAnnotationHandlers) {
		this.formFieldAnnotationHandlers = formFieldAnnotationHandlers;
	}

	public void setAppAnnotationHandlers(Map<Class, AppAnnotationHandler> appAnnotationHandlers) {
		this.appAnnotationHandlers = appAnnotationHandlers;
	}

	public void setPageAnnotationHandlers(Map<Class, PageAnnotationHandler> pageAnnotationHandlers) {
		this.pageAnnotationHandlers = pageAnnotationHandlers;
	}

	public static class FormFactoryPrototype {

		public static Map<Class, AnnotationHandler> getDefaultHandlers() {
			Map<Class, AnnotationHandler> handlers = new HashMap<>();
			handlers.put(FormField.class, new FormFieldAnnotationHandler());
			handlers.put(Validator.class, new ValidatorAnnotationHandler());
			handlers.put(Validators.class, new ValidatorsAnnotationHandler());
			handlers.put(ValueProviderValue.class, new ValueProviderValueAnnotationHandler());
			handlers.put(ValueProviderValues.class, new ValueProviderValuesAnnotationHandler());
			handlers.put(CheckBoxValue.class, new CheckBoxValueAnnotationHandler());
			return handlers;
		}
	}

	public static class AppFactoryPrototype {

		private static Map<Class, AppAnnotationHandler> getDefaultHandlers() {
			Map<Class, AppAnnotationHandler> handlers = new HashMap<>();
			handlers.put(Menu.class, new MenuAnnotationHandler());
			handlers.put(MenuItem.class, new MenuItemAnnotationHandler());
			return handlers;
		}
	}

	public static class PageFactoryPrototype {

		private static Map<Class, PageAnnotationHandler> getDefaultHandlers() {
			Map<Class, PageAnnotationHandler> handlers = new HashMap<>();
			handlers.put(AppPage.class, new AppPageAnnotationHandler());
			return handlers;
		}
	}
}
