package org.fenxui.application.view.factory.ootb.form.marshall;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TextField;
import org.fenxui.application.view.bind.widget.custom.SkinnableNumberField;
import org.fenxui.application.view.components.option.FieldOption;

public class ObjectMarshallStrategy implements MarshallStrategy<ObjectProperty, TextField> {

	@Override
	public void execute(FieldOption<ObjectProperty> fieldOption, TextField textField) {
		ObjectProperty property = fieldOption.getValue();
		if (textField instanceof SkinnableNumberField) {
			SkinnableNumberField numberField = (SkinnableNumberField) textField;
			property.addListener((observable, oldValue, newValue) -> numberField.notifySourceValueChanged(fieldOption.getFieldName(), oldValue, newValue));
		}
	}

}
