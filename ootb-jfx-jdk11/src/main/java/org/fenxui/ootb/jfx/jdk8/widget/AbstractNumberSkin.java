package org.fenxui.ootb.jfx.jdk8.widget;

import com.jfoenix.skins.JFXTextFieldSkin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.ootb.jfx.widget.util.SkinnedTextInputValidationFilterChain;
import org.fenxui.ootb.jfx.widget.util.BigDecimalSkinnedNumberExtractor;
import org.fenxui.ootb.jfx.widget.util.NumberParseStrategy;
import org.fenxui.ootb.jfx.widget.INumberFieldSkin;
import org.fenxui.ootb.jfx.widget.SkinnableNumberField;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public abstract class AbstractNumberSkin extends JFXTextFieldSkin<SkinnableNumberField> implements INumberFieldSkin {
	private static final Logger logger = LogManager.getLogger(AbstractNumberSkin.class);
	private final SkinnableNumberField skinnable;
	private final BigDecimalSkinnedNumberExtractor extractor;
	private final DecimalFormat formatter;
	private final String symbol;
	private final boolean leadingSymbol;
	private final SkinnedTextInputValidationFilterChain inputValidationStrategy;

	public AbstractNumberSkin(final SkinnableNumberField skinnable) {
		super(skinnable);
		this.skinnable = skinnable;

		skinnable.setReplaceText(this::accept);
		// Align the text to the right
		skinnable.setAlignment(Pos.BASELINE_RIGHT);

		// Whenever the text of the textField changes update the value.
		if (skinnable.isEditable()) {
			skinnable.focusedProperty().addListener(new ChangeListener<Boolean>() {//clicking\tabbing out of field should trigger an update
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (!newValue) { //don't trigger update before they get a chance to type anything (only do on loss of focus)
						updateValue();
					}
				}
			});
			skinnable.setOnAction(event -> updateValue());//hitting <enter> on a field should trigger an update
		}

		NumberParseStrategy strategy = skinnable.getNumberParseStrategy();
		this.inputValidationStrategy = new SkinnedTextInputValidationFilterChain(strategy);
		this.leadingSymbol = strategy.isLeadingSymbol();
		// Make sure the text is updated to the initial state of the MoneyField value
		formatter =	strategy.getFormatter();
		symbol = strategy.getSymbol();
		extractor = new BigDecimalSkinnedNumberExtractor(formatter, symbol, leadingSymbol);
		updateText();
		skinnable.setNumberSkin(this);
	}

	protected void updateText() {
		BigDecimal value = skinnable.getValue();
		updateText(value);
	}

	@Override
	public void updateText(BigDecimal value) {
		skinnable.setText(value == null ? "" : formatter.format(value));
	}

	protected void updateValue() {
		boolean updateText = !"".equals(skinnable.getText());
		try {
			BigDecimal value = skinnable.getValue();
			BigDecimal newValue = extractor.getNumber(skinnable);

			if (value == null || newValue == null || !value.equals(newValue)) {
				skinnable.setValue(newValue);
				updateText = true;
			}
		} catch (ParseException ex) {
			logger.error("Error parsing BigDecimal from String: " + skinnable.getText(), ex);
		} finally {
			if (updateText) {
				executeUpdate(skinnable, formatter);
			}
		}
	}

	private boolean accept(String text) {
		return inputValidationStrategy.requiresFieldUpdate(text);
	}

	protected abstract void executeUpdate(SkinnableNumberField skinnable, DecimalFormat formatter);

}
