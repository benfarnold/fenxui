package org.fenxui.application.view.bind.widget.custom;

import java.text.DecimalFormat;
import java.util.Currency;

public class CurrencyFieldSkin extends AbstractNumberSkin {

	public CurrencyFieldSkin(final SkinnableNumberField skinnable) {
		super(skinnable);
	}

	@Override
	protected void executeUpdate(SkinnableNumberField skinnable, DecimalFormat formatter) {
		int caretPosition = skinnable.getCaretPosition();
		Currency currency = formatter.getCurrency();
		skinnable.insertText(0, currency.getSymbol());
		skinnable.positionCaret(caretPosition + currency.getSymbol().length());
	}
}
