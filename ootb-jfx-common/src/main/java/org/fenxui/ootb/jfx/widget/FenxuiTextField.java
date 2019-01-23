package org.fenxui.ootb.jfx.widget;

import com.jfoenix.controls.JFXTextField;
import org.fenxui.application.view.bind.widget.ActionableWidget;
import org.fenxui.application.view.bind.widget.UniqueValidatableControl;

import java.util.UUID;

public class FenxuiTextField extends JFXTextField implements ActionableWidget, JFXValidatableControl {
	private final String uniqueId = UUID.randomUUID().toString();

	@Override
	public String getUniqueId() {
		return uniqueId;
	}
}
