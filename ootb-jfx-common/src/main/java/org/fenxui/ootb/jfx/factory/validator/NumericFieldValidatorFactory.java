package org.fenxui.ootb.jfx.factory.validator;

import org.fenxui.api.factory.ValidatorFactory;
import org.fenxui.api.validator.IValidator;
import org.fenxui.application.view.components.option.ValidatorOptions;
import org.fenxui.ootb.jfx.widget.validator.FenxuiNumericValidator;
import org.fenxui.core.exception.FenxuiInitializationException;

public class NumericFieldValidatorFactory<V extends IValidator> implements ValidatorFactory<ValidatorOptions, V> {

	@Override
	public V create(ValidatorOptions validatorOptions) throws FenxuiInitializationException {
		return (V) new FenxuiNumericValidator(validatorOptions.getMessage());
	}
}
