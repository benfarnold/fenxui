package org.fenxui.application.view.components.validator;

import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.ColorOptions;
import org.fenxui.application.view.factory.handler.util.GlyphUtil;

public class FenxuiRequiredFieldValidator extends RequiredFieldValidator implements ConditionalValidator {
	/**
	 * conditional can be bound to another variable so something might be conditionally required
	 * default true value means it is required by default
	 */
	private BooleanProperty conditional = new SimpleBooleanProperty(true);
	
	public FenxuiRequiredFieldValidator(String message) throws FenxuiInitializationException {
		super();
		setMessage(message);
		setIcon(GlyphUtil.getGlyph("icomoon.svg.exclamation-triangle, warning", ColorOptions.REDDISH));
		conditional.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {//reset errors, since no longer required
					hasErrors.set(false);
					Node node = getSrcControl();
					if (node != null) {
						node.requestFocus();
					}
				}
			}
		});
	}

	@Override
	protected void eval() {
		if (conditional.get()) {
			super.eval();
		}
	}

	@Override
	public BooleanProperty conditionalProperty() {
		return conditional;
	}
}
