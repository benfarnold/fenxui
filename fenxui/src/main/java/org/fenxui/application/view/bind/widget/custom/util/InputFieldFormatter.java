package org.fenxui.application.view.bind.widget.custom.util;

/**
 * Screens typed input based on strategy.
 * Used to allow the user to type in a field when we're also updating it with things like decimal places, currency symbols etc
 */
public class InputFieldFormatter {

	private final InputValidationStrategy validationStrategy;

	public InputFieldFormatter(InputValidationStrategy validationStrategy) {
		this.validationStrategy = validationStrategy;
	}

	public boolean updateField(String text) {
		return validationStrategy.requiresFieldUpdate(text);
	}
}
