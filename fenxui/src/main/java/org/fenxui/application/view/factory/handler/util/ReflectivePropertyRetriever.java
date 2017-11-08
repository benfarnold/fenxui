package org.fenxui.application.view.factory.handler.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.beans.property.Property;
import javafx.scene.Node;

public class ReflectivePropertyRetriever {
	private final String methodName;

	public ReflectivePropertyRetriever(String name) {
		this.methodName = name + "Property";
	}

	public Property getProperty(Node node) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method m = node.getClass().getMethod(methodName);
		return (Property) m.invoke(node);
	}
}
