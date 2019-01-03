package org.fenxui.application.view.components.validator;

import com.jfoenix.validation.NumberValidator;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.ColorOptions;
import org.fenxui.application.view.factory.handler.util.GlyphUtil;

public class FenxuiNumericValidator extends NumberValidator {
	
	public FenxuiNumericValidator(String message) throws FenxuiInitializationException {
		super();
		setMessage(message);
		setIcon(GlyphUtil.getGlyph("icomoon.svg.exclamation-triangle, warning", ColorOptions.REDDISH));
	}
}
