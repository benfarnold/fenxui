package org.fenxui.ootb.jfx.widget.util.validation;

import org.fenxui.ootb.jfx.widget.util.NumberParseStrategy;

import java.util.Optional;

public class DoubleSymbolValidator implements ValidationFilter {
	private final NumberParseStrategy numberParseStrategy;

	public DoubleSymbolValidator(NumberParseStrategy numberParseStrategy) {
		this.numberParseStrategy = numberParseStrategy;
	}

	@Override
	public Optional<Boolean> getResult(ValidationFilterContext ctx) {
		String text = ctx.getText();
		String symbol = numberParseStrategy.getSymbol();
		if (numberParseStrategy.isLeadingSymbol() && text.startsWith(symbol)) {
			if (text.equals(symbol)) {
				return Optional.of(false);
			}
			text = text.substring(symbol.length()).trim();
			if (text.contains(symbol)) {
				return Optional.of(false);//eliminate double-prepends
			}
		} else if (!numberParseStrategy.isLeadingSymbol() && text.endsWith(symbol)) {
			text = text.substring(0, text.length() - 1).trim();
			if (text.contains(symbol)) {
				return Optional.of(false);//eliminate double-appends
			}
		}
		ctx.setText(text);
		return Optional.empty();
	}
}
