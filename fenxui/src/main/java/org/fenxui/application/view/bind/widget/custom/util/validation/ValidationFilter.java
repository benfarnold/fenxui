package org.fenxui.application.view.bind.widget.custom.util.validation;

import java.util.Optional;

public interface ValidationFilter {
	Optional<Boolean> getResult(ValidationFilterContext ctx);
}
