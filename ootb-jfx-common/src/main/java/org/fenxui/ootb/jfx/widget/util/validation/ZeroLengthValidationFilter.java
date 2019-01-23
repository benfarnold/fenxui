package org.fenxui.ootb.jfx.widget.util.validation;

import java.util.Optional;

public class ZeroLengthValidationFilter implements ValidationFilter {

	@Override
	public Optional<Boolean> getResult(ValidationFilterContext ctx) {
		return Optional.ofNullable(ctx.getText().length() == 0 ? true : null);
	}
}
