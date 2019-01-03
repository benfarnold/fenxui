package org.fenxui.application.view.decorator;

import javafx.scene.control.Label;
import org.fenxui.application.view.factory.ootb.AppFactory;

public class AbstractDecorator<T> {

	protected final ComponentDecorator<T, Object> decorator;

	public AbstractDecorator(ComponentDecorator decorator) {
		this.decorator = decorator;
	}

	protected Label makeLabel(String text, String cssClass) {
		Label label = new Label(text);
		label.getStyleClass().add(cssClass);
		return label;
	}

	protected Label makeTitle(String value, String cssClass) {
		return makeLabel(value, cssClass + "-title");
	}
	
	public AppFactory getAppFactory() {
		return decorator.getAppFactory();
	}
}
