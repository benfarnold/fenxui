package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import org.fenxui.annotation.ValueProviderValue;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.components.option.FieldOption.DisplayValue;
import org.fenxui.application.view.components.valueprovider.StaticValueProvider;

public class ValueProviderValueAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) {
		ValueProviderValue value = (ValueProviderValue) annotation;
		FieldOption fieldOption = fieldContext.getActiveFieldOption();
		StaticValueProvider valueProvider = fieldOption.getValueProvider();
		if (valueProvider == null) {
			valueProvider = new StaticValueProvider();
			fieldOption.setValueProvider(valueProvider);
		}
		valueProvider.addValue(new DisplayValue(value.key(), value.value()));
	}	

}
