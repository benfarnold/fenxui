package org.fenxui.samples.simple.viewmodel.pages;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.Validator;
import org.fenxui.application.view.components.option.ValidatorOptions;

@AppPage("Tab B")
public class SimpleTabPageB {
	private final TabbedSettings tabbedSettings;

	@FormField(label = "Required when Tab A enabled")
	@Validator(type = ValidatorOptions.REQUIRED, message = "From email is required", evalExpression="#{SimpleTabPageA.enable} eq 'true'")
	private final StringProperty conditionallyRequiredField = new SimpleStringProperty();

	public SimpleTabPageB(TabbedSettings tabbedSettings) {
		this.tabbedSettings = tabbedSettings;
	}

	public String getConditionallyRequiredField() {
		return conditionallyRequiredField.get();
	}

	public StringProperty conditionallyRequiredFieldProperty() {
		return conditionallyRequiredField;
	}

	public void setConditionallyRequiredField(String conditionallyRequiredField) {
		this.conditionallyRequiredField.set(conditionallyRequiredField);
	}
}
