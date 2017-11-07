package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import org.fenxui.annotation.FormFieldButton;
import org.fenxui.application.view.bind.widget.FenxuiButton;

public class FormFieldButtonAnnotationHandler implements AnnotationHandler, ActionHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException {
		FormFieldButton formFieldDecorator = (FormFieldButton) annotation;
		FenxuiButton button = new FenxuiButton(formFieldDecorator.value());
		setOnAction(fieldContext.getSource(), button, formFieldDecorator.action());
		RowContext rowContex = fieldContext.getRowContext();
		rowContex.addToRow(button, 2);
	}

}
