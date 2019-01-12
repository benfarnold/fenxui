package org.fenxui.application.view.components.option;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.valueprovider.StaticValueProvider;
import org.fenxui.application.view.components.valueprovider.ValueProvider;
import org.fenxui.application.view.factory.handler.FieldPostProcessor;
import org.fenxui.application.view.factory.ootb.form.FieldFactory;
import org.fenxui.application.view.factory.ootb.form.marshall.BindBiDirectionalMarshallStrategy;
import org.fenxui.application.view.factory.ootb.form.marshall.MarshallStrategy;

import java.util.ArrayList;
import java.util.List;

public class FieldOption<T extends Property> {
	private final String fieldName;
	private final BooleanProperty readOnly = new SimpleBooleanProperty(false);
	private String name;
	private T value;
	private LayoutSection layoutSection;
	private List<FieldPostProcessor> fieldPostProcessors = new ArrayList<>();
	private List<ValidatorBase> validators = new ArrayList<>();
	private ValueProvider valueProvider;
	private FieldFactory fieldFactory;
	private MarshallStrategy marshallStrategy = new BindBiDirectionalMarshallStrategy();
	private boolean bindFieldToPaneWidth;

	public boolean isBindFieldToPaneWidth() {
		return bindFieldToPaneWidth;
	}

	public void setBindFieldToPaneWidth(boolean bindFieldToPaneWidth) {
		this.bindFieldToPaneWidth = bindFieldToPaneWidth;
	}

	public FieldOption(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}
	
	public void addValidator(ValidatorBase validator) {
		validators.add(validator);
	}
	
	public List<ValidatorBase> getValidators() {
		return validators;
	}

	public String getName() {
		return name;
	}

	public LayoutSection getLayoutSection() {
		return layoutSection;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLayoutSection(LayoutSection layoutSection) {
		this.layoutSection = layoutSection;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ValueProvider getValueProvider() {
		return valueProvider;
	}

	public void setValueProvider(ValueProvider valueProvider) {
		this.valueProvider = valueProvider;
	}

	public FieldFactory getFieldFactory() {
		return fieldFactory;
	}

	public void setFieldFactory(FieldFactory fieldFactory) {
		this.fieldFactory = fieldFactory;
	}

	public void executeMarshallStrategy(Node node) {
		marshallStrategy.execute(this, node);
	}

	public void setMarshallStrategy(MarshallStrategy marshallStrategy) {
		this.marshallStrategy = marshallStrategy;
	}

	public ValueProvider getOrDefaultValueProvider() {
		ValueProvider valueProvider = getValueProvider();
		if (valueProvider == null) {
			valueProvider = new StaticValueProvider();
			setValueProvider(valueProvider);
		}
		return valueProvider;
	}

	public boolean isReadOnly() {
		return readOnly.get();
	}

	public BooleanProperty readOnlyProperty() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly.set(readOnly);
	}

	public void addPostprocessor(FieldPostProcessor fieldPostProcessor) {
		fieldPostProcessors.add(fieldPostProcessor);
	}

	public void postprocess() throws FenxuiInitializationException {
		for (FieldPostProcessor postProcessor : fieldPostProcessors) {
			postProcessor.postProcess(this);
		}
	}

	public static class DisplayValue {
		protected String saveValue;
		protected String displayValue;

		public DisplayValue(String saveValue, String displayValue) {
			this.saveValue = saveValue;
			this.displayValue = displayValue;
		}
		public DisplayValue(String saveValue) {
			this(saveValue, saveValue);
		}

		public String getSaveValue() {
			return saveValue;
		}

		public String getDisplayValue() {
			return displayValue;
		}
	}

	public enum LayoutSection {
		DEFAULT, ADVANCED
	}
}
