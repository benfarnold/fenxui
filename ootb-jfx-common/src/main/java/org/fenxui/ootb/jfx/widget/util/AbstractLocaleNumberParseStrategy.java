package org.fenxui.ootb.jfx.widget.util;

import java.text.DecimalFormat;
import java.util.Locale;

public class AbstractLocaleNumberParseStrategy {
	protected final DecimalFormat formatter;
	protected String symbol;
	protected final boolean leadingSymbol;
	private final Locale locale;

	public AbstractLocaleNumberParseStrategy(Locale locale, DecimalFormat decimalFormat, boolean leadingSymbol) {
		this.locale = locale;
		formatter = decimalFormat;
		formatter.setParseBigDecimal(true);
		this.leadingSymbol = leadingSymbol;
	}

	public DecimalFormat getFormatter() {
		return formatter;
	}

	public String getSymbol() {
		return symbol;
	}

	public boolean isLeadingSymbol() {
		return leadingSymbol;
	}

	public Locale getLocale() {
		return locale;
	}
}
