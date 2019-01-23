package org.fenxui.ootb.jfx.factory;

import org.fenxui.api.factory.FieldFactory;
import org.fenxui.api.factory.ValidatorFactory;
import org.fenxui.api.prototype.OOTBFieldValidators;
import org.fenxui.application.view.factory.AbstractFactoryInitContext;
import org.fenxui.ootb.jfx.factory.field.CheckBoxFieldFactory;
import org.fenxui.ootb.jfx.factory.field.PasswordFieldFactory;
import org.fenxui.ootb.jfx.factory.field.SelectFieldFactory;
import org.fenxui.ootb.jfx.factory.field.TextFieldFactory;
import org.fenxui.ootb.jfx.factory.validator.NumericFieldValidatorFactory;
import org.fenxui.ootb.jfx.factory.validator.RequiredFieldValidatorFactory;

import java.util.HashMap;
import java.util.Map;

import static org.fenxui.api.prototype.OOTBFieldPrototypes.*;

public class FactoryInitContext extends AbstractFactoryInitContext {

	public FactoryInitContext() {
		super(FieldPrototype.getDefaultCustomFieldFactories(), ValidatorPrototype.getDefaultValidatorFactories());
	}

	/**
	 * Custom field types allowing you to customize skins of number fields, etc
	 */
	public interface FieldPrototype {
		static Map<String, FieldFactory> getDefaultCustomFieldFactories() {
			Map<String, FieldFactory> factories = new HashMap<>();
			factories.put(TEXT_FIELD, new TextFieldFactory());
			factories.put(PASSWORD_FIELD, new PasswordFieldFactory());
			factories.put(SELECT_FIELD, new SelectFieldFactory());
			factories.put(CHECKBOX_FIELD, new CheckBoxFieldFactory());
			return factories;
		}
	}


	public interface ValidatorPrototype {
		static Map<String, ValidatorFactory> getDefaultValidatorFactories() {
			Map<String, ValidatorFactory> factories = new HashMap<>();
			factories.put(OOTBFieldValidators.NUMERIC, new NumericFieldValidatorFactory());
			factories.put(OOTBFieldValidators.REQUIRED, new RequiredFieldValidatorFactory());
			return factories;
		}
	}
}
