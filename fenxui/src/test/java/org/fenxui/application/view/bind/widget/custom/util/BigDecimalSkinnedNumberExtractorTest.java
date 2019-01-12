package org.fenxui.application.view.bind.widget.custom.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BigDecimalSkinnedNumberExtractorTest {
	private final NumberParseStrategy percentStrategy = new PercentNumberParseStrategy();
	private final NumberParseStrategy currencyStrategy = new CurrencyNumberParseStrategy();

	@Test
	public void extractsPositiveUS() throws Exception {
		{
			BigDecimal result = extractNumber("10.23%", percentStrategy);
			assertThat(result.doubleValue(), is(0.1023));
		}
		{
			BigDecimal result = extractNumber("$10.23", currencyStrategy);
			assertThat(result.doubleValue(), is(10.23));
		}
	}

	@Test
	public void extractsNegativeUS() throws Exception {
		{
			BigDecimal result = extractNumber("-10.23%", percentStrategy);
			assertThat(result.doubleValue(), is(-0.1023));
		}
		{
			BigDecimal result = extractNumber("-$10.23", currencyStrategy);
			assertThat(result.doubleValue(), is(-10.23));
		}
		{
			BigDecimal result = extractNumber("-10.23", currencyStrategy);
			assertThat(result.doubleValue(), is(-10.23));
		}
		{
			BigDecimal result = extractNumber("($54,800.00)", currencyStrategy);
			assertThat(result.doubleValue(), is(-54800.00));
		}
	}

	private BigDecimal extractNumber(String number, NumberParseStrategy strategy) throws Exception {
		BigDecimalSkinnedNumberExtractor extractor = new BigDecimalSkinnedNumberExtractor(strategy.getFormatter(), strategy.getSymbol(), strategy.isLeadingSymbol());
		return extractor.getNumber(BigDecimal.ZERO, number);
	}

}
