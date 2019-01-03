package org.fenxui.application.view.bind.widget;

import com.jfoenix.controls.JFXPasswordField;
import java.util.UUID;

public class FenxuiPasswordField extends JFXPasswordField implements UniqueValidatableControl {
	private final String uniqueId = UUID.randomUUID().toString();

	@Override
	public String getUniqueId() {
		return uniqueId;
	}
}
