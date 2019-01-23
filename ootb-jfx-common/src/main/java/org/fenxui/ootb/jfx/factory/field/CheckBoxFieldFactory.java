package org.fenxui.ootb.jfx.factory.field;

import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.marshall.CheckBoxMarshallStrategy;

public class CheckBoxFieldFactory implements org.fenxui.api.factory.FieldFactory<FieldOption> {
	@Override
	public Node create(FieldOption option) {
		JFXCheckBox checkBox = new JFXCheckBox();

		if (!(option.getValue() instanceof BooleanProperty)) {
			option.setMarshallStrategy(new CheckBoxMarshallStrategy());
		}
		option.executeMarshallStrategy(checkBox);
		return checkBox;
	}
}
