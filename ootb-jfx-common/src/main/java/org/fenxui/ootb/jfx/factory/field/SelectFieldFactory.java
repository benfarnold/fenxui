package org.fenxui.ootb.jfx.factory.field;

import javafx.scene.Node;
import org.fenxui.api.factory.FieldFactory;
import org.fenxui.application.marshall.ComboBoxMarshallStrategy;
import org.fenxui.ootb.jfx.widget.FenxuiComboBox;
import org.fenxui.application.view.components.option.FieldOption;

public class SelectFieldFactory implements FieldFactory<FieldOption> {

	@Override
	public Node create(FieldOption option) {
		FenxuiComboBox<String> comboBox = new FenxuiComboBox<>();
		comboBox.setFenxuiValidators(option.getValidators());
		option.setMarshallStrategy(new ComboBoxMarshallStrategy());
		option.executeMarshallStrategy(comboBox);
		return comboBox;
	}
}
