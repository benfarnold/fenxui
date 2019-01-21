package org.fenxui.application.view.factory.ootb.form;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.geometry.Insets;
import javafx.scene.Node;
import org.fenxui.application.view.bind.widget.FenxuiTextField;
import org.fenxui.application.view.components.option.FieldOption;

public class TextFieldFactory extends AbstractFieldFactory {

	@Override
	public Node create(FieldOption option) {
		ValidatorBase[] validatorArray = prepValidators(option);

		JFXTextField textField = new FenxuiTextField();
		textField.setPadding(new Insets(5));
		if (validatorArray != null) {
			textField.setValidators(validatorArray);
		}
		textField.editableProperty().bind(option.readOnlyProperty().not());
//		textField.disableProperty().bind(option.readOnlyProperty());

		option.executeMarshallStrategy(textField);

		return textField;
	}
}
