package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import org.fenxui.application.view.bind.widget.FenxuiButton;
import org.fenxui.annotation.FieldButton;
import org.fenxui.application.exception.FenxuiInitializationException;

public class FieldButtonAnnotationHandler implements AnnotationHandler, ActionHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		FieldButton formFieldDecorator = (FieldButton) annotation;
		FenxuiButton button = new FenxuiButton(formFieldDecorator.value());
		setOnAction(fieldContext.getSource(), button, formFieldDecorator.action());
		fieldContext.getRowContext().setFormFieldButton(button);
	}

}
