package org.fenxui.ootb.jfx.factory.field;

import javafx.geometry.Insets;
import javafx.scene.Node;
import org.fenxui.api.factory.FieldFactory;
import org.fenxui.ootb.jfx.widget.FenxuiTextField;
import org.fenxui.application.view.components.option.FieldOption;

public class TextFieldFactory implements FieldFactory<FieldOption> {

	@Override
	public Node create(FieldOption option) {
		FenxuiTextField textField = new FenxuiTextField();
		textField.setPadding(new Insets(5));
		textField.setFenxuiValidators(option.getValidators());
		textField.editableProperty().bind(option.readOnlyProperty().not());

		option.executeMarshallStrategy(textField);

		return textField;
	}
}
