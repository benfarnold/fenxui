package org.fenxui.application.view.bind.widget;

import com.jfoenix.controls.JFXComboBox;
import java.util.UUID;

public class FenxuiComboBox <T> extends JFXComboBox<T> implements UniqueValidatableControl {
	private final String uniqueId = UUID.randomUUID().toString();

	@Override
	public String getUniqueId() {
		return uniqueId;
	}

}
