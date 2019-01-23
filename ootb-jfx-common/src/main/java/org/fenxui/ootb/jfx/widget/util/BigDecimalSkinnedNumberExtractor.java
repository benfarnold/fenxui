package org.fenxui.ootb.jfx.widget.util;

import org.fenxui.ootb.jfx.widget.SkinnableNumberField;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class BigDecimalSkinnedNumberExtractor {
	private final DecimalFormat formatter;
	private final String symbol;
	private final boolean leadingSymbol;

	public BigDecimalSkinnedNumberExtractor(DecimalFormat formatter, String symbol, boolean leadingSymbol) {
		this.formatter = formatter;
		this.symbol = symbol;
		this.leadingSymbol = leadingSymbol;
	}

	public BigDecimal getNumber(SkinnableNumberField skinnable) throws ParseException {
		BigDecimal value = skinnable.getValue();
		String text = skinnable.getText() == null ? "" : skinnable.getText().trim();
		return getNumber(value, text);
	}

	public BigDecimal getNumber(BigDecimal value, String text) throws ParseException {
		BigDecimal newValue;
		BigDecimal multiplicand = BigDecimal.ONE;
		if ("".equals(text) || symbol.equals(text)) {
			newValue = BigDecimal.ZERO;
		}else {
			if (leadingSymbol && !text.startsWith(symbol)) {
				if (text.startsWith("-")) {
					multiplicand = BigDecimal.valueOf(-1);
					if (text.contains(symbol)) {
						text = text.substring(1);
					} else {
						text = symbol + text.substring(1);
					}
				} else if (text.startsWith("(")) {
					if (!text.contains(symbol)) {
						text = "(" + symbol + text.substring(1);
					}
				} else {
					text = symbol + text;
				}
			} else if (!leadingSymbol && !text.endsWith(symbol)) {
				text = text + symbol;
			}

			Number n = formatter.parse(text);
			newValue = n instanceof BigDecimal ? (BigDecimal) n : new BigDecimal(n.doubleValue());
		}
		return newValue.multiply(multiplicand);
	}

}
