package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.Property;
import org.fenxui.annotation.BindProperty;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.bind.widget.FenxuiCheckBox;
import org.fenxui.application.view.factory.handler.util.ReflectivePropertyRetriever;

public class BindPropertyAnnotationHandler implements AnnotationHandler {
	private static final Logger logger = Logger.getLogger(BindPropertyAnnotationHandler.class.getName());

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		BindProperty bindProperty = (BindProperty) annotation;
		String fieldProperty = bindProperty.bindProperty();
		String refFieldName = bindProperty.controllingFieldName();
		String refFieldProperty = bindProperty.controllingFieldProperty();
		fieldContext.addPostProcessor(node -> {
			NodeContext targetField = fieldContext.getFormContext().get(refFieldName);
			logger.log(Level.INFO, "refField: {0}{1}", new Object[]{refFieldName, targetField == null ? "=null" : "=notnull"});
			ReflectivePropertyRetriever targetPropertyRetriever = new ReflectivePropertyRetriever(refFieldProperty);
			logger.info("about to get refFieldProperty: " + refFieldProperty);
			Property drivingProperty = targetPropertyRetriever.getProperty(targetField.getNode());
			ReflectivePropertyRetriever thisPropertyRetriever = new ReflectivePropertyRetriever(fieldProperty);
			logger.info("about to get fieldProperty: " + fieldProperty);
			Property observingProperty = thisPropertyRetriever.getProperty(node);
			observingProperty.bind(drivingProperty);
			FenxuiCheckBox checkbox = new FenxuiCheckBox();
			checkbox.selectedProperty();
		});
	}

}
