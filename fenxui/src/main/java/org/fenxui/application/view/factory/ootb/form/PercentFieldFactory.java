package org.fenxui.application.view.factory.ootb.form;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.Skin;
import org.fenxui.application.view.bind.widget.custom.PercentageFieldSkin;
import org.fenxui.application.view.bind.widget.custom.SkinnableNumberField;
import org.fenxui.application.view.bind.widget.custom.util.PercentNumberParseStrategy;

public class PercentFieldFactory extends SkinnableFieldFactory {

	@Override
	protected SkinnableNumberField getNewInstance(ValidatorBase[] validatorArray) {
		return new SkinnableNumberField(new PercentNumberParseStrategy()) {
			@Override
			protected Skin<?> createDefaultSkin() {
				return new PercentageFieldSkin(this);
			}
		};
	}


}
