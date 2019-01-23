package org.fenxui.ootb.jfx.factory.field;

import javafx.geometry.Insets;
import javafx.scene.Node;
import org.fenxui.api.factory.FieldFactory;
import org.fenxui.ootb.jfx.widget.FenxuiPasswordField;
import org.fenxui.application.view.components.option.FieldOption;

public class PasswordFieldFactory implements FieldFactory<FieldOption> {

	@Override
	public Node create(FieldOption option) {
		FenxuiPasswordField passwordField = new FenxuiPasswordField();
		passwordField.setPadding(new Insets(5));
		passwordField.setFenxuiValidators(option.getValidators());
		option.executeMarshallStrategy(passwordField);
		return passwordField;
	}
}
