package org.fenxui.application.view.factory.ootb.form;


import com.jfoenix.validation.base.ValidatorBase;
import org.fenxui.application.view.components.option.FieldOption;

import java.util.List;

public abstract class AbstractFieldFactory implements FieldFactory {

	protected ValidatorBase[] prepValidators(FieldOption option) {
		List<ValidatorBase> validators = option.getValidators();
		if (!validators.isEmpty()) {
			return validators.toArray(new ValidatorBase[validators.size()]);
		} else {
			return null;
		}
	}

}
