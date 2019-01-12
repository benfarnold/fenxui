package org.fenxui.application.view.factory.ootb.form;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.geometry.Insets;
import javafx.scene.Node;
import org.fenxui.application.view.bind.widget.FenxuiPasswordField;
import org.fenxui.application.view.components.option.FieldOption;

public class PasswordFieldFactory extends AbstractFieldFactory {

	@Override
	public Node create(FieldOption option) {
		ValidatorBase[] validatorArray = prepValidators(option);
		JFXPasswordField passwordField = new FenxuiPasswordField();
		passwordField.setPadding(new Insets(5));
		if (validatorArray != null) {
			passwordField.setValidators(validatorArray);
		}
		option.executeMarshallStrategy(passwordField);
		return passwordField;
	}
}
