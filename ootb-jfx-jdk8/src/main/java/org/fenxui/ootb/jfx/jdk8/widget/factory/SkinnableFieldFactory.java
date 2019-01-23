package org.fenxui.ootb.jfx.jdk8.widget.factory;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import org.fenxui.api.factory.FieldFactory;
import org.fenxui.application.marshall.DoubleMarshallStrategy;
import org.fenxui.application.marshall.IntegerMarshallStrategy;
import org.fenxui.ootb.jfx.widget.marshall.ObjectMarshallStrategy;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.ootb.jfx.widget.SkinnableNumberField;

public abstract class SkinnableFieldFactory implements FieldFactory<FieldOption> {

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
		SkinnableNumberField skinnableNumberField = getNewInstance();
		skinnableNumberField.setFenxuiValidators(option.getValidators());
		skinnableNumberField.editableProperty().bind(option.readOnlyProperty().not());
		updateMarshallStrategy(option);
		option.executeMarshallStrategy(skinnableNumberField);
		return skinnableNumberField;
	}

	protected abstract SkinnableNumberField getNewInstance();

}

