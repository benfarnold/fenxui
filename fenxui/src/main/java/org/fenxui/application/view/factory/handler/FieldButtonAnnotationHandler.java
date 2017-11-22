package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import org.fenxui.application.view.bind.widget.FenxuiButton;
import org.fenxui.annotation.FieldButton;

public class FieldButtonAnnotationHandler implements AnnotationHandler, ActionHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException {
		FieldButton formFieldDecorator = (FieldButton) annotation;
		FenxuiButton button = new FenxuiButton(formFieldDecorator.value());
		setOnAction(fieldContext.getSource(), button, formFieldDecorator.action());
		fieldContext.getRowContext().setFormFieldButton(button);
	}

}
