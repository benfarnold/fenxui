package org.fenxui.ootb.jfx.widget;

import com.jfoenix.controls.JFXPasswordField;
import org.fenxui.application.view.bind.widget.UniqueValidatableControl;

import java.util.UUID;

public class FenxuiPasswordField extends JFXPasswordField implements JFXValidatableControl {
	private final String uniqueId = UUID.randomUUID().toString();

	@Override
	public String getUniqueId() {
		return uniqueId;
	}
}
