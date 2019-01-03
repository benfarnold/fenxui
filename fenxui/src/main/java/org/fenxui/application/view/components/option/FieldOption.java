package org.fenxui.application.view.components.option;

import com.jfoenix.controls.*;
import com.jfoenix.validation.base.ValidatorBase;
import java.util.ArrayList;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.fenxui.application.view.bind.widget.FenxuiComboBox;
import org.fenxui.application.view.bind.widget.FenxuiPasswordField;
import org.fenxui.application.view.bind.widget.FenxuiTextField;
import org.fenxui.application.view.components.valueprovider.ValueProvider;

public class FieldOption {
	private final String fieldName;
	private String name;
	private StringProperty value;
	private Type type;
	private LayoutSection layoutSection;
	private ValuesProvider valuesProvider;
	private List<ValidatorBase> validators = new ArrayList<>();
	private ValidatorBase[] validatorArray;
	private ValueProvider valueProvider;

	public FieldOption(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	private void prepValidators() {
		if (validatorArray == null && !validators.isEmpty()) {
			validatorArray = validators.toArray(new ValidatorBase[validators.size()]);
		}
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

	public Type getType() {
		return type;
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

	public void setType(Type type) {
		this.type = type;
	}

	public StringProperty getValue() {
		return value;
	}

	public void setValue(StringProperty value) {
		this.value = value;
	}

	public <T extends ValueProvider> T getValueProvider() {
		return (T) valueProvider;
	}

	public void setValueProvider(ValueProvider valueProvider) {
		this.valueProvider = valueProvider;
	}

	public static class ValuesProvider {
		protected StringProperty monitored;
		protected DisplayValue[] values;

		public ValuesProvider(StringProperty monitored, DisplayValue...values) {
			this.monitored = monitored;
			this.values = values;
		}

		public ValuesProvider(DisplayValue...values) {
			this(null, values);
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
	}

	public enum Type {
		TEXT {
			@Override
			public Node create(FieldOption option) {
				return getTextField(option);
			}
		},
		TEXT_EXPANDING {
			@Override
			public Node create(FieldOption option) {
				return getTextField(option);
			}
		},
		PASSWORD{
			@Override
			public Node create(FieldOption option) {
				option.prepValidators();
				JFXPasswordField passwordField = new FenxuiPasswordField();
				passwordField.setPadding(new Insets(5));
				if (option.validatorArray != null) {
					passwordField.setValidators(option.validatorArray);
				}
				passwordField.textProperty().bindBidirectional(option.value);
				return passwordField;
			}
		},SELECT{
			@Override
			public Node create(FieldOption option) {
				option.prepValidators();
				ValueProvider valueProvider = option.valueProvider;
				List<DisplayValue> values = valueProvider.getValues();
				
				//create a two-way map
				Map<String, String> selectedToSaveValuesMap = values.stream().collect(Collectors.toMap(v->v.displayValue,v->v.saveValue));
				Map<String, String> saveToDisplayValuesMap = values.stream().collect(Collectors.toMap(v->v.saveValue,v->v.displayValue));
				
				JFXComboBox<String> comboBox = new FenxuiComboBox<>();
				if (option.validatorArray != null) {
					comboBox.setValidators(option.validatorArray);
				}
				comboBox.getItems().addAll(selectedToSaveValuesMap.keySet());
				if (!StringUtils.isEmpty(option.value.get())) {
					String displayValue = saveToDisplayValuesMap.get(option.value.get());
					comboBox.selectionModelProperty().get().select(displayValue);
				}
				comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
					String saveValue = selectedToSaveValuesMap.get(newValue);
					option.value.set(saveValue);
				});
				return comboBox;
			}
		},MULTISELECT {
			@Override
			public Node create(FieldOption option) {
				return null;//TODO
			}
		},CHECKBOX{
			@Override
			public Node create(FieldOption option) {
				JFXCheckBox checkBox = new JFXCheckBox();
				ValueProvider valueProvider = option.valueProvider;
				List<DisplayValue> values = valueProvider.getValues();
				String selectedValue = values.get(0).saveValue;
				String unselectedValue = values.get(1).saveValue;
				checkBox.setSelected(selectedValue.equalsIgnoreCase(option.value.get()));//initial values
				//propagate changes
				checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
					if (newValue) {
						option.value.set(selectedValue);
					} else {
						option.value.set(unselectedValue);
					}
				});
				return checkBox;
			}
		}, CHIPVIEW {
			@Override
			public Node create(FieldOption option) {
				JFXChipView<String> chipView = new JFXChipView<>();
				chipView.setStyle("-fx-background-color: WHITE;");
				chipView.setChipFactory((chip, value) -> new GuidChip(chip, value));
				return chipView;
			}
		};

		private static Node getTextField(FieldOption option) {
			option.prepValidators();
			JFXTextField textField = new FenxuiTextField();
			textField.setPadding(new Insets(5));
			if (option.validatorArray != null) {
				textField.setValidators(option.validatorArray);
			}
			textField.textProperty().bindBidirectional(option.value);
			StringProperty hintProperty = textField.promptTextProperty();

			if (option.valuesProvider != null && option.valuesProvider.monitored != null) {
				Map<String, String> valueToHintMap = Stream.of(option.valuesProvider.values).collect(Collectors.toMap(v->v.saveValue, v->v.displayValue));
				StringProperty monitored = option.valuesProvider.monitored;
				monitored.addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						String hint = valueToHintMap.get(newValue);
						hintProperty.set(hint);
					}
				});
			}
			return textField;
		}

		public abstract Node create(FieldOption option);

		private static class GuidChip extends JFXDefaultChip<String> {

			public GuidChip(JFXChipView<String> chip, String value) {
				super(chip, value);

				Label label = (Label) root.getChildren().get(0);
				label.setMaxWidth(275);
				label.setWrapText(false);
			}
		}
	}

	public enum LayoutSection {
		DEFAULT, ADVANCED
	}
}
