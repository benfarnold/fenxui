package org.fenxui.application.state;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.Property;

/**
 * Facilitate global binding of JavaFX properties
 */
public enum GlobalHive {
	INSTANCE;
	private final Map<String, Property> globalBindings = new HashMap<>();

	public void bind(String globalId, Property observingProperty) {
		globalBindings.put(globalId, observingProperty);
	}
}
