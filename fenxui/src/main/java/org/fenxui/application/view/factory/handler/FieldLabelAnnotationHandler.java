package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import javafx.scene.control.Label;
import org.fenxui.annotation.FieldLabel;
import org.fenxui.application.exception.FenxuiInitializationException;

public class FieldLabelAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		FieldLabel labelAnnotation = (FieldLabel) annotation;
		Label label = new Label(labelAnnotation.value());
		fieldContext.getRowContext().setLabel(label);
	}

}
