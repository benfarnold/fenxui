package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.beans.property.Property;
import javafx.scene.Node;
import org.fenxui.annotation.BindProperty;
import org.fenxui.application.view.bind.widget.FenxuiCheckBox;

public class BindPropertyAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException {
		BindProperty bindProperty = (BindProperty) annotation;
		String fieldProperty = bindProperty.bindProperty();
		String refFieldName = bindProperty.controllingFieldName();
		String refFieldProperty = bindProperty.controllingFieldProperty();
		fieldContext.addPostProcessor(node -> {
			NodeContext targetField = fieldContext.getFormContext().get(refFieldName);
			ReflectivePropertyRetriever targetPropertyRetriever = new ReflectivePropertyRetriever(refFieldProperty);
			Property drivingProperty = targetPropertyRetriever.getProperty(targetField.getNode());
			ReflectivePropertyRetriever thisPropertyRetriever = new ReflectivePropertyRetriever(fieldProperty);
			Property observingProperty = thisPropertyRetriever.getProperty(node);
			observingProperty.bind(drivingProperty);
			FenxuiCheckBox checkbox = new FenxuiCheckBox();
			checkbox.selectedProperty();
		});
	}

	private class ReflectivePropertyRetriever {

		private final String methodName;

		private ReflectivePropertyRetriever(String name) {
			this.methodName = name + "Property";
		}

		Property getProperty(Node node) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
			Method m = node.getClass().getMethod(methodName);
			return (Property) m.invoke(node);
		}
	}

}
