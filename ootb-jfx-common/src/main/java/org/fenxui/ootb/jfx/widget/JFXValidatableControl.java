package org.fenxui.ootb.jfx.widget;

import com.jfoenix.controls.base.IFXValidatableControl;
import com.jfoenix.validation.base.ValidatorBase;
import org.fenxui.api.validator.IValidator;
import org.fenxui.application.view.bind.widget.UniqueValidatableControl;

import java.util.List;

public interface JFXValidatableControl extends UniqueValidatableControl, IFXValidatableControl {

	default void setFenxuiValidators(List<IValidator> iValidators) {
		if (!iValidators.isEmpty()) {
			ValidatorBase[] validators = iValidators.toArray(new ValidatorBase[iValidators.size()]);
			setValidators(validators);
		}
	}
}
