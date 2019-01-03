package org.fenxui.application.view.bind.widget;

import com.jfoenix.controls.JFXTextField;
import java.util.UUID;

public class FenxuiTextField extends JFXTextField implements ActionableWidget, UniqueValidatableControl {
	private final String uniqueId = UUID.randomUUID().toString();

	@Override
	public String getUniqueId() {
		return uniqueId;
	}
}
