package org.fenxui.ootb.jfx.widget.util;

import java.text.DecimalFormat;
import java.util.Locale;

public interface NumberParseStrategy {
	DecimalFormat getFormatter();
	String getSymbol();
	boolean isLeadingSymbol();

	boolean validateTrailing(DecimalFormat formatter, String text, int decimalSeparatorIndex);

	Locale getLocale();
}
