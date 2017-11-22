package org.fenxui.application.view.bind.widget;

import org.fenxui.application.data.bind.DataBindable;
import javafx.scene.control.TextField;

public class FenxuiTextField extends TextField implements ActionableWidget, DataBindable<String> {

	@Override
	public String getValue() {
		return getText();
	}

	@Override
	public void setValue(String value) {
		setText(value);
	}

}
