package org.fenxui.application.view.factory;

import org.fenxui.annotation.*;
import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.annotation.el.ExpressionFormField;
import org.fenxui.api.factory.FieldFactory;
import org.fenxui.api.factory.ValidatorFactory;
import org.fenxui.application.view.factory.handler.*;
import org.fenxui.application.view.factory.handler.app.MenuAnnotationHandler;
import org.fenxui.application.view.factory.handler.app.MenuItemAnnotationHandler;
import org.fenxui.application.view.factory.handler.el.ExpressionFormFieldAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.AppPageAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * All annotations to be handled and their handlers Anything not contained here
 * will not be processed.
 */
public abstract class AbstractFactoryInitContext {

	private Map<Class, AnnotationHandler> formFieldAnnotationHandlers = FormFactoryPrototype.getDefaultHandlers();
	private Map<Class, PageAnnotationHandler> pageAnnotationHandlers = PageFactoryPrototype.getDefaultHandlers();

	private Map<String, FieldFactory> fieldFactories;
	private Map<String, ValidatorFactory> validatorFactories;

	public AbstractFactoryInitContext(Map<String, FieldFactory> fieldFactories, Map<String, ValidatorFactory> validatorFactories) {
		this.fieldFactories = fieldFactories;
		this.validatorFactories = validatorFactories;
	}

	public AbstractFactoryInitContext(Map<String, FieldFactory> fieldFactories) {
		this(fieldFactories, new HashMap<>());
	}

	public Map<Class, AnnotationHandler> getFormFieldAnnotationHandlers() {
		return formFieldAnnotationHandlers;
	}
	public Map<Class, PageAnnotationHandler> getPageAnnotationHandlers() {
		return pageAnnotationHandlers;
	}

	public void setFormFieldAnnotationHandlers(Map<Class, AnnotationHandler> formFieldAnnotationHandlers) {
		this.formFieldAnnotationHandlers = formFieldAnnotationHandlers;
	}
	public void setPageAnnotationHandlers(Map<Class, PageAnnotationHandler> pageAnnotationHandlers) {
		this.pageAnnotationHandlers = pageAnnotationHandlers;
	}

	public Map<String, FieldFactory> getFieldFactories() {
		return fieldFactories;
	}

	public void setFieldFactories(Map<String, FieldFactory> fieldFactories) {
		this.fieldFactories = fieldFactories;
	}

	public Map<String, ValidatorFactory> getValidatorFactories() {
		return validatorFactories;
	}

	public void setValidatorFactories(Map<String, ValidatorFactory> validatorFactories) {
		this.validatorFactories = validatorFactories;
	}

	/**
	 * Handlers scoped to fields
	 */
	public interface FormFactoryPrototype {

		static Map<Class, AnnotationHandler> getDefaultHandlers() {
			Map<Class, AnnotationHandler> handlers = new HashMap<>();
			handlers.put(FormField.class, new FormFieldAnnotationHandler());
			handlers.put(Validator.class, new ValidatorAnnotationHandler());
			handlers.put(Validators.class, new ValidatorsAnnotationHandler());
			handlers.put(ValueProviderValue.class, new ValueProviderValueAnnotationHandler());
			handlers.put(ValueProviderValues.class, new ValueProviderValuesAnnotationHandler());
			handlers.put(CheckBoxValue.class, new CheckBoxValueAnnotationHandler());
			handlers.put(ExpressionFormField.class, new ExpressionFormFieldAnnotationHandler());
			handlers.put(MenuItem.class, new MenuItemAnnotationHandler());
			return handlers;
		}
	}

	/**
	 * Handlers scoped to class
	 */
	public interface PageFactoryPrototype {

		static Map<Class, PageAnnotationHandler> getDefaultHandlers() {
			Map<Class, PageAnnotationHandler> handlers = new HashMap<>();
			handlers.put(Menu.class, new MenuAnnotationHandler());
			handlers.put(AppPage.class, new AppPageAnnotationHandler());
			return handlers;
		}
	}

}
