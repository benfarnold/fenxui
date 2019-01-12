package org.fenxui.application.view.bind.widget.custom.util.validation;

import java.util.Optional;

public class ZeroLengthValidationFilter implements ValidationFilter {

	@Override
	public Optional<Boolean> getResult(ValidationFilterContext ctx) {
		return Optional.ofNullable(ctx.getText().length() == 0 ? true : null);
	}
}
