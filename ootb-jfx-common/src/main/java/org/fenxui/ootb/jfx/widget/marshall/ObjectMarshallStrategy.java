package org.fenxui.ootb.jfx.widget.marshall;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TextField;
import org.fenxui.application.marshall.MarshallStrategy;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.ootb.jfx.widget.SkinnableNumberField;

public class ObjectMarshallStrategy implements MarshallStrategy<ObjectProperty, TextField> {

	@Override
	public void execute(FieldOption<ObjectProperty> fieldOption, TextField textField) {
		ObjectProperty property = fieldOption.getValue();
		if (textField instanceof SkinnableNumberField) {
			SkinnableNumberField numberField = (SkinnableNumberField) textField;
			property.addListener((observable, oldValue, newValue) -> {
				numberField.notifySourceValueChanged(fieldOption.getFieldName(), oldValue, newValue);
			});
			numberField.valueProperty().addListener((observable, oldValue, newValue) -> property.set(newValue));
		}
	}

}
