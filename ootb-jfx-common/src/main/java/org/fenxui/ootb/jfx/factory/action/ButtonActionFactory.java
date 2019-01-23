package org.fenxui.ootb.jfx.factory.action;

import javafx.scene.Node;
import org.fenxui.api.factory.ActionFactory;
import org.fenxui.application.view.action.ReflectiveActionEventHandler;
import org.fenxui.application.view.components.option.MethodOption;
import org.fenxui.ootb.jfx.widget.action.FenxuiButton;

public class ButtonActionFactory implements ActionFactory<MethodOption> {

	@Override
	public Node create(MethodOption option) {
		FenxuiButton button = new FenxuiButton(option.getLabel());
		button.getStyleClass().add(option.getCssClass());
		button.setOnAction(new ReflectiveActionEventHandler(option.getMethod(), option.getPageObject(), null));
		return button;
	}
}
