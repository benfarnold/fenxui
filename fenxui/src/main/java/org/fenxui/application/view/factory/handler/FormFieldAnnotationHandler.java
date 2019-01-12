package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import org.fenxui.annotation.FormField;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.ootb.form.marshall.DoubleMarshallStrategy;
import org.fenxui.application.view.factory.ootb.form.marshall.IntegerMarshallStrategy;

public class FormFieldAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		FormField formField = (FormField) annotation;
		FieldOption fieldOption = fieldContext.getActiveFieldOption();
		fieldOption.setName(formField.label());
		fieldOption.setLayoutSection(formField.section());
		fieldOption.setFieldFactory(fieldContext.getFieldFactory(formField.factory()));
		fieldOption.setBindFieldToPaneWidth(formField.dynamicWidth());

		Property valueField = fieldOption.getValue();
		if (valueField instanceof IntegerProperty) {
			fieldOption.setMarshallStrategy(new IntegerMarshallStrategy());
		} else if (valueField instanceof DoubleProperty) {
			fieldOption.setMarshallStrategy(new DoubleMarshallStrategy());
		}
	}

}
