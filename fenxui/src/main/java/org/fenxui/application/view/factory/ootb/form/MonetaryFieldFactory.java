package org.fenxui.application.view.factory.ootb.form;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.Skin;
import org.fenxui.application.view.bind.widget.custom.CurrencyFieldSkin;
import org.fenxui.application.view.bind.widget.custom.SkinnableNumberField;
import org.fenxui.application.view.bind.widget.custom.util.CurrencyNumberParseStrategy;

public class MonetaryFieldFactory extends SkinnableFieldFactory {

	@Override
	protected SkinnableNumberField getNewInstance(ValidatorBase[] validatorArray) {
		return new SkinnableNumberField(new CurrencyNumberParseStrategy()) {
			@Override
			protected Skin<?> createDefaultSkin() {
				return new CurrencyFieldSkin(this);
			}
		};
	}
}
