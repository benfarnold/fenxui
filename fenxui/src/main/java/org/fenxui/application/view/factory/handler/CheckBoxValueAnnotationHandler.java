package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import org.fenxui.annotation.CheckBoxValue;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.components.option.FieldOption.DisplayValue;
import org.fenxui.application.view.components.valueprovider.StaticValueProvider;

public class CheckBoxValueAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		CheckBoxValue checkBoxValue = (CheckBoxValue) annotation;
		FieldOption fieldOption = fieldContext.getActiveFieldOption();
		StaticValueProvider staticValueProvider = fieldOption.getValueProvider();
		if (staticValueProvider == null) {
			staticValueProvider = new StaticValueProvider();
			fieldOption.setValueProvider(staticValueProvider);
		}
		staticValueProvider.addValue(new DisplayValue(checkBoxValue.checked()));//order matters, true
		staticValueProvider.addValue(new DisplayValue(checkBoxValue.unchecked()));//then false
	}

}