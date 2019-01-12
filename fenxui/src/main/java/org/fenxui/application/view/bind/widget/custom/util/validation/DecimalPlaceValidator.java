package org.fenxui.application.view.bind.widget.custom.util.validation;

import org.fenxui.application.view.bind.widget.custom.util.NumberParseStrategy;

import java.text.DecimalFormatSymbols;
import java.util.Optional;

public class DecimalPlaceValidator implements ValidationFilter {
	private final NumberParseStrategy numberParseStrategy;

	public DecimalPlaceValidator(NumberParseStrategy numberParseStrategy) {
		this.numberParseStrategy = numberParseStrategy;
	}

	@Override
	public Optional<Boolean> getResult(ValidationFilterContext ctx) {
		String text = ctx.getText();
		final DecimalFormatSymbols symbols = new DecimalFormatSymbols(numberParseStrategy.getLocale());
		final char decimalSeparator = symbols.getMonetaryDecimalSeparator();

		int decimalSeparatorIndex = text.indexOf(decimalSeparator);
		if (decimalSeparatorIndex < 0) {
			return Optional.of(true);
		}
		if (nonDigitAfterDecimal(text, decimalSeparatorIndex)) {
			return Optional.of(false);
		}

		if (!numberParseStrategy.validateTrailing(numberParseStrategy.getFormatter(), text, decimalSeparatorIndex)) {
			return Optional.of(false);
		}
		return Optional.empty();
	}

	private boolean nonDigitAfterDecimal(String text, int decimalSeparatorIndex) {
		for (int i = decimalSeparatorIndex+1; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (!Character.isDigit(ch)) {
				return true;
			}
		}
		return false;
	}
}
