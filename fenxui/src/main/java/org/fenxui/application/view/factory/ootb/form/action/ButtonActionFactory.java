package org.fenxui.application.view.factory.ootb.form.action;

import javafx.scene.Node;
import org.fenxui.application.view.action.ReflectiveActionEventHandler;
import org.fenxui.application.view.bind.widget.FenxuiButton;
import org.fenxui.application.view.factory.handler.action.MethodOption;

public class ButtonActionFactory implements ActionFactory {

	@Override
	public Node create(MethodOption option) {
		FenxuiButton button = new FenxuiButton(option.getLabel());
		button.getStyleClass().add(option.getCssClass());
		button.setOnAction(new ReflectiveActionEventHandler(option.getMethod(), option.getPageObject(), null));
		return button;
	}
}
