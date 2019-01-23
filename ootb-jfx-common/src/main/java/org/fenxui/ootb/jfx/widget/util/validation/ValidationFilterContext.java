package org.fenxui.ootb.jfx.widget.util.validation;

public class ValidationFilterContext {
	private String text;

	public ValidationFilterContext(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
