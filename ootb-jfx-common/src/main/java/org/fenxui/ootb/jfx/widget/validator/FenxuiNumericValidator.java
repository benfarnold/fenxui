package org.fenxui.ootb.jfx.widget.validator;

import com.jfoenix.validation.NumberValidator;
import org.fenxui.api.validator.IValidator;
import org.fenxui.application.view.components.option.ColorOptions;
import org.fenxui.core.exception.FenxuiInitializationException;
import org.fenxui.ootb.jfx.widget.util.GlyphUtil;

public class FenxuiNumericValidator extends NumberValidator implements IValidator {
	
	public FenxuiNumericValidator(String message) throws FenxuiInitializationException {
		super();
		setMessage(message);
		setIcon(GlyphUtil.getGlyph("icomoon.svg.exclamation-triangle, warning", ColorOptions.REDDISH));
	}
}
