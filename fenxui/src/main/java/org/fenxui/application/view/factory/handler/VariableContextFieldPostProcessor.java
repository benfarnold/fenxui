package org.fenxui.application.view.factory.handler;

import javafx.beans.property.StringProperty;
import org.fenxui.application.el.VariableContext;
import org.fenxui.application.view.components.option.FieldOption;

public class VariableContextFieldPostProcessor implements FieldPostProcessor {
	private final String fieldName;
	private final String variableName;
	private final VariableContext variableContext;

	public VariableContextFieldPostProcessor(String fieldName, String variableName, VariableContext variableContext) {
		this.fieldName = fieldName;
		this.variableName = variableName;
		this.variableContext = variableContext;
	}

	@Override
	public void postProcess(FieldOption fieldOption) {
		if (fieldOption.getFieldName().equalsIgnoreCase(fieldName)) {
			StringProperty value = fieldOption.getValue();
			variableContext.addVariable(variableName, value);
		}
	}
}
