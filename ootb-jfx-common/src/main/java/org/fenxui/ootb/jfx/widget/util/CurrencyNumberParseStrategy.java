package org.fenxui.ootb.jfx.widget.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class CurrencyNumberParseStrategy extends AbstractLocaleNumberParseStrategy implements NumberParseStrategy {

	public CurrencyNumberParseStrategy(Locale locale) {
		super(locale, (DecimalFormat) NumberFormat.getCurrencyInstance(locale), true);
		Currency currency = getFormatter().getCurrency();
		symbol = currency.getSymbol();
	}

	public CurrencyNumberParseStrategy() {
		this(Locale.getDefault());
	}

	@Override
	public boolean validateTrailing(DecimalFormat formatter, String text, int decimalSeparatorIndex) {
		if (decimalSeparatorIndex != -1) {
			int trailingLength = text.substring(decimalSeparatorIndex + 1).length();
			if (trailingLength > formatter.getCurrency().getDefaultFractionDigits()) {
				return false;
			}
		}
		return true;
	}

}
