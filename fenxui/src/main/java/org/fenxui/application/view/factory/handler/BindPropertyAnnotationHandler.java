package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import javafx.beans.property.Property;
import org.fenxui.annotation.BindProperty;
import org.fenxui.application.view.bind.widget.FenxuiCheckBox;
import org.fenxui.application.view.factory.handler.util.ReflectivePropertyRetriever;

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

}
