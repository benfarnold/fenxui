package org.fenxui.application.view.bind.widget.custom;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.application.view.bind.widget.custom.util.NumberParseStrategy;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class SkinnableNumberField extends JFXTextField {
	private static final Logger logger = LogManager.getLogger(SkinnableNumberField.class);

	private final ObjectProperty<BigDecimal> value = new SimpleObjectProperty<>(this, "value");
	private final NumberParseStrategy numberParseStrategy;
	private AbstractNumberSkin numberSkin;
	private Predicate<String> replaceText;

	public SkinnableNumberField(NumberParseStrategy numberParseStrategy) {
		this.numberParseStrategy = numberParseStrategy;
	}

	public NumberParseStrategy getNumberParseStrategy() {
		return numberParseStrategy;
	}

	public final BigDecimal getValue() {
		return value.get();
	}

	public final void setValue(BigDecimal value) {
		this.value.set(value);
	}

	public final ObjectProperty<BigDecimal> valueProperty() {
		return value;
	}

	@Override
	public void replaceText(int start, int end, String text) {
		String t = getText() == null ? "" : getText();
		t = t.substring(0, start) + text + t.substring(end);
		if (replaceText.test(t)) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		String t = getText() == null ? "" : getText();
		int start = Math.min(getAnchor(), getCaretPosition());
		int end = Math.max(getAnchor(), getCaretPosition());
		t = t.substring(0, start) + text + t.substring(end);
		if (replaceText.test(t)) {
			super.replaceSelection(text);
		}
	}

	void setReplaceText(Predicate<String> replaceText) {
		this.replaceText = replaceText;
	}

	void setNumberSkin(AbstractNumberSkin numberSkin) {
		this.numberSkin = numberSkin;
	}

	public void notifySourceValueChanged(String fieldName, Object old, Object newVal) {
		if (logger.isTraceEnabled()) {
			logger.trace(fieldName + " source changed: " + old + " -> " + newVal);
		}
		if (numberSkin != null) {
			if (newVal instanceof BigDecimal) {
				numberSkin.updateText((BigDecimal) newVal);
			} else if (newVal instanceof  Double) {
				numberSkin.updateText(BigDecimal.valueOf((Double) newVal));
			} else if (newVal instanceof Long) {
				numberSkin.updateText(BigDecimal.valueOf((Long) newVal));
			}
		}
	}
}
