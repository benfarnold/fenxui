package org.fenxui.application.view.factory.ootb.form;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import org.fenxui.application.view.bind.widget.custom.SkinnableNumberField;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.ootb.form.marshall.DoubleMarshallStrategy;
import org.fenxui.application.view.factory.ootb.form.marshall.IntegerMarshallStrategy;
import org.fenxui.application.view.factory.ootb.form.marshall.ObjectMarshallStrategy;

public abstract class SkinnableFieldFactory extends AbstractFieldFactory {

	protected void updateMarshallStrategy(FieldOption option) {
		if (option.getValue() instanceof IntegerProperty) {
			option.setMarshallStrategy(new IntegerMarshallStrategy());
		} else if (option.getValue() instanceof DoubleProperty) {
			option.setMarshallStrategy(new DoubleMarshallStrategy());
		} else if (option.getValue() instanceof ObjectProperty) {
			option.setMarshallStrategy(new ObjectMarshallStrategy());
		}
	}

	@Override
	public Node create(FieldOption option) {
		ValidatorBase[] validatorArray = prepValidators(option);
		SkinnableNumberField skinnableNumberField = getNewInstance(validatorArray);
		if (validatorArray != null) {
			skinnableNumberField.setValidators(validatorArray);
		}
		skinnableNumberField.editableProperty().bind(option.readOnlyProperty().not());
//		skinnableNumberField.disableProperty().bind(option.readOnlyProperty());
		updateMarshallStrategy(option);
		option.executeMarshallStrategy(skinnableNumberField);
		return skinnableNumberField;
	}

	protected abstract SkinnableNumberField getNewInstance(ValidatorBase[] validatorArray);

}

