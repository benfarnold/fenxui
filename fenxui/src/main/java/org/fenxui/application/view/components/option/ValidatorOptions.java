package org.fenxui.application.view.components.option;

import com.jfoenix.validation.base.ValidatorBase;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.validator.FenxuiNumericValidator;
import org.fenxui.application.view.components.validator.FenxuiRequiredFieldValidator;

public enum ValidatorOptions {
	REQUIRED{
		@Override
		public ValidatorBase create(String message) throws FenxuiInitializationException {
			return new FenxuiRequiredFieldValidator(message);
		}
	}, NUMERIC{
		@Override
		public ValidatorBase create(String message) throws FenxuiInitializationException {
			return new FenxuiNumericValidator(message);
		}
	};

	public abstract ValidatorBase create(String message) throws FenxuiInitializationException;
}
