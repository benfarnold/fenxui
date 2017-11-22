package org.fenxui.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse system properties denoted with $(my.property)
 */
public class PropertyParser {

	private static final Pattern PROPERTY_REGEX = Pattern.compile("(\\$\\((([a-zA-Z.0-9])*)\\))");
	public String parse(String text) {
		String parsed = text;
		Matcher m = PROPERTY_REGEX.matcher(text);
		if (m.find()) {
			String matchSubstring = m.group();
			String property = m.group(2);
			String resolved = System.getProperty(property);
			parsed = text.replace(matchSubstring, resolved);

		}
		return parsed;
	}

}
