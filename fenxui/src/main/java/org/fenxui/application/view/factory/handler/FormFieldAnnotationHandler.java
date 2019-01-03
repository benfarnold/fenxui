package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import org.fenxui.annotation.FormField;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;

public class FormFieldAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		FormField formField = (FormField) annotation;
		FieldOption fieldOption = fieldContext.getActiveFieldOption();
		fieldOption.setName(formField.label());
		fieldOption.setLayoutSection(formField.section());
		fieldOption.setType(formField.type());
	}

}
