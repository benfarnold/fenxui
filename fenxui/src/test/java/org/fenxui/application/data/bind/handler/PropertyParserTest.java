package org.fenxui.application.data.bind.handler;

import org.fenxui.util.PropertyParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertyParserTest {

	@Test
	public void parsesSystemPropety() throws Exception {
		String original = "$(user.home)/.fenxui";
		String expected = System.getProperty("user.home") + "/.fenxui";
		PropertyParser propertyParser = new PropertyParser();
		String result = propertyParser.parse(original);
		assertEquals(expected, result);
	}

	@Test
	public void parsesPlainText() throws Exception {
		String original = "/.fenxui";
		String expected = "/.fenxui";
		PropertyParser propertyParser = new PropertyParser();
		String result = propertyParser.parse(original);
		assertEquals(expected, result);
	}
}
