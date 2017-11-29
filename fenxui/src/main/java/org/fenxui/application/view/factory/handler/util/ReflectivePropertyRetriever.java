package org.fenxui.application.view.factory.handler.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.beans.property.Property;
import javafx.scene.Node;
import org.fenxui.application.exception.FenxuiInitializationException;

public class ReflectivePropertyRetriever {
	private final String methodName;

	public ReflectivePropertyRetriever(String name) {
		this.methodName = name + "Property";
	}

	public Property getProperty(Node node) throws FenxuiInitializationException {
		try {
			Method m = node.getClass().getMethod(methodName);
			return (Property) m.invoke(node);
		} catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException ex) {
			throw new FenxuiInitializationException(ex);
		}
	}
}
