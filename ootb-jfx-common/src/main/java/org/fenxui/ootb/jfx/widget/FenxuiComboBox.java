package org.fenxui.ootb.jfx.widget;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.base.IFXValidatableControl;
import org.fenxui.application.view.bind.widget.UniqueValidatableControl;

import java.util.UUID;

public class FenxuiComboBox <T> extends JFXComboBox<T> implements JFXValidatableControl {
	private final String uniqueId = UUID.randomUUID().toString();

	@Override
	public String getUniqueId() {
		return uniqueId;
	}

}
