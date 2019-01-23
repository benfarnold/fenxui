package org.fenxui.ootb.jfx.widget.util;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SkinnedTextInputValidationFilterChainTest {
	private final SkinnedTextInputValidationFilterChain percentStrategy = new SkinnedTextInputValidationFilterChain(new PercentNumberParseStrategy(Locale.US));
	private final SkinnedTextInputValidationFilterChain currencyStrategy = new SkinnedTextInputValidationFilterChain(new CurrencyNumberParseStrategy(Locale.US));

	@Test
	public void emptyString() {
		assertTrue(validate("", percentStrategy));
		assertTrue(validate("", currencyStrategy));

		assertTrue(validate(" ", percentStrategy));
		assertTrue(validate(" ", currencyStrategy));
	}

	@Test
	public void integer() {
		assertTrue(validate("123", percentStrategy));
		assertTrue(validate("123", currencyStrategy));

		assertTrue(validate("123", percentStrategy));
		assertTrue(validate("123", currencyStrategy));
	}

	@Test
	public void trailingDecimal() {
		assertTrue(validate("0.01", percentStrategy));
		assertTrue(validate("0.01", currencyStrategy));

		assertTrue(validate("0.001", percentStrategy));
		assertFalse(validate("0.001", currencyStrategy));
	}

	@Test
	public void thousandsSeparator() {
		assertTrue(validate("1,000,000", percentStrategy));
		assertTrue(validate("1,000,000", currencyStrategy));

		assertTrue(validate("100,000.01", percentStrategy));
		assertTrue(validate("100,000.01", currencyStrategy));
	}

	@Test
	public void thousandsSeparatorGerman() {
		SkinnedTextInputValidationFilterChain germanPercent = new SkinnedTextInputValidationFilterChain(new PercentNumberParseStrategy(Locale.GERMANY));
		SkinnedTextInputValidationFilterChain germanCurrency = new SkinnedTextInputValidationFilterChain(new CurrencyNumberParseStrategy(Locale.GERMANY));
		assertTrue(validate("1.000.000", germanPercent));
		assertTrue(validate("1.000.000", germanCurrency));

		assertTrue(validate("100.000,01", germanPercent));
		assertTrue(validate("100.000,01", germanCurrency));
	}

	@Test
	public void doubleSymbol() {
		assertFalse(validate("1%%", percentStrategy));
		assertFalse(validate("$$1", currencyStrategy));

		assertFalse(validate("1.00%%", percentStrategy));
		assertFalse(validate("$$1.00", currencyStrategy));
	}

	private boolean validate(String text, SkinnedTextInputValidationFilterChain strategy) {
		return strategy.requiresFieldUpdate(text);
	}
}
