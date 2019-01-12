package org.fenxui.application.view.bind.widget.custom.util;

import org.fenxui.application.view.bind.widget.custom.util.validation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SkinnedTextInputValidationFilterChain implements InputValidationStrategy {
	private final List<ValidationFilter> validationFilters;

	public SkinnedTextInputValidationFilterChain(NumberParseStrategy numberParseStrategy) {
		this.validationFilters = Arrays.asList(
				new ZeroLengthValidationFilter(),
				new DoubleSymbolValidator(numberParseStrategy),
				new DecimalPlaceValidator(numberParseStrategy)
		);
	}

	@Override
	public boolean requiresFieldUpdate(String text) {
		text = text.trim();
		ValidationFilterContext context = new ValidationFilterContext(text.trim());
		Optional<Boolean> result = validationFilters.stream()
				.filter(f->f.getResult(context).isPresent())
				.map(f->f.getResult(context).get())
				.findFirst();

		if (result.isPresent()) {
			return result.get();
		} else {
			return true;
		}
	}

}
