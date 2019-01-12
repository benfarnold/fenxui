package org.fenxui.application.view.bind.widget.custom.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class PercentNumberParseStrategy extends AbstractLocaleNumberParseStrategy implements NumberParseStrategy {

	public PercentNumberParseStrategy(Locale locale) {
		super(locale,  (DecimalFormat) NumberFormat.getPercentInstance(locale), false);
		this.symbol = "%";
	}

	public PercentNumberParseStrategy() {
		this(Locale.getDefault());
	}

	@Override
	public DecimalFormat getFormatter() {
		return formatter;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public boolean isLeadingSymbol() {
		return leadingSymbol;
	}

	@Override
	public boolean validateTrailing(DecimalFormat formatter, String text, int decimalSeparatorIndex) {
		return true;
	}
}
