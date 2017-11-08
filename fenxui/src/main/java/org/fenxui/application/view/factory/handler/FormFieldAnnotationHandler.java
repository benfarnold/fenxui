package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.fenxui.annotation.FormField;

public class FormFieldAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws IllegalAccessException {
		FormField formFieldAnnotation = (FormField) annotation;
		Label label = new Label(formFieldAnnotation.value());
		RowContext rowContext = fieldContext.getRowContext();
		rowContext.addToRow(label, 0);

		Node widget = (Node) FieldUtils.readField(fieldContext.getField(), fieldContext.getSource(), true);
		widget.autosize();
		rowContext.addToRow(widget, 1);
		fieldContext.setNode(widget);
	}

}
