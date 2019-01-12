package org.fenxui.application.view.bind.widget.custom;

import java.text.DecimalFormat;

public class PercentageFieldSkin extends AbstractNumberSkin {

	public PercentageFieldSkin(SkinnableNumberField skinnable) {
		super(skinnable);
	}

	@Override
	protected void executeUpdate(SkinnableNumberField skinnable, DecimalFormat formatter) {
		int caretPosition = skinnable.getCaretPosition();
		skinnable.appendText("%");
		skinnable.positionCaret(caretPosition);
	}
}
