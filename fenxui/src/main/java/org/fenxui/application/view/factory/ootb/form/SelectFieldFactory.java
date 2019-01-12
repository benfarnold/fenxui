package org.fenxui.application.view.factory.ootb.form;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.Node;
import org.fenxui.application.view.bind.widget.FenxuiComboBox;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.components.valueprovider.ValueProvider;
import org.fenxui.application.view.factory.ootb.form.marshall.ComboBoxMarshallStrategy;

import java.util.List;

public class SelectFieldFactory extends AbstractFieldFactory {
	@Override
	public Node create(FieldOption option) {
		ValidatorBase[] validatorArray = prepValidators(option);
		ValueProvider valueProvider = option.getValueProvider();
		List<FieldOption.DisplayValue> values = valueProvider.getValues();

		JFXComboBox<String> comboBox = new FenxuiComboBox<>();
		if (validatorArray != null) {
			comboBox.setValidators(validatorArray);
		}
		option.setMarshallStrategy(new ComboBoxMarshallStrategy());
		option.executeMarshallStrategy(comboBox);
		return comboBox;
	}
}
