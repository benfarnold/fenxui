package org.fenxui.application.view.factory;

import org.fenxui.annotation.*;
import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.annotation.el.ExpressionFormField;
import org.fenxui.application.view.factory.handler.*;
import org.fenxui.application.view.factory.handler.app.MenuAnnotationHandler;
import org.fenxui.application.view.factory.handler.app.MenuItemAnnotationHandler;
import org.fenxui.application.view.factory.handler.el.ExpressionFormFieldAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.AppPageAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;
import org.fenxui.application.view.factory.ootb.form.*;

import java.util.HashMap;
import java.util.Map;

/**
 * All annotations to be handled and their handlers Anything not contained here
 * will not be processed.
 */
public class FactoryInitContext {

	private Map<Class, AnnotationHandler> formFieldAnnotationHandlers = FormFactoryPrototype.getDefaultHandlers();
	private Map<Class, PageAnnotationHandler> pageAnnotationHandlers = PageFactoryPrototype.getDefaultHandlers();
	private Map<String, FieldFactory> fieldFactories = FieldPrototype.getDefaultCustomFieldFactories();

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

	/**
	 * Custom field types allowing you to customize skins of number fields, etc
	 */
	public interface FieldPrototype {
		String MONETARY_FIELD = "monetary";
		String PERCENT_FIELD = "percent";
		String TEXT_FIELD = "text";
		String PASSWORD_FIELD = "password";
		String SELECT_FIELD = "select";
		String CHECKBOX_FIELD = "checkbox";

		static Map<String, FieldFactory> getDefaultCustomFieldFactories() {
			Map<String, FieldFactory> factories = new HashMap<>();
			factories.put(TEXT_FIELD, new TextFieldFactory());
			factories.put(PASSWORD_FIELD, new PasswordFieldFactory());
			factories.put(SELECT_FIELD, new SelectFieldFactory());
			factories.put(CHECKBOX_FIELD, new CheckBoxFieldFactory());
			factories.put(MONETARY_FIELD, new MonetaryFieldFactory());
			factories.put(PERCENT_FIELD, new PercentFieldFactory());
			return factories;
		}
	}
}
