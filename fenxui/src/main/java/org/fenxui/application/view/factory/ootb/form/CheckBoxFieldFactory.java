package org.fenxui.application.view.factory.ootb.form;

import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.ootb.form.marshall.CheckBoxMarshallStrategy;

public class CheckBoxFieldFactory extends AbstractFieldFactory {
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
