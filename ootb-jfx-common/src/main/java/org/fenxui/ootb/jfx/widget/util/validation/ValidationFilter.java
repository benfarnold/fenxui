package org.fenxui.ootb.jfx.widget.util.validation;

import java.util.Optional;

public interface ValidationFilter {
	Optional<Boolean> getResult(ValidationFilterContext ctx);
}
