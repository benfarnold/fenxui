package org.fenxui.application.view.factory.handler;

import java.lang.reflect.Method;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import org.fenxui.application.view.action.ReflectiveActionEventHandler;
import org.fenxui.application.view.bind.widget.ActionableWidget;

public interface ActionHandler {

	default void setOnAction(Object source, Node widget, String action) throws NoSuchMethodException {
		if (!"".equals(action)) {
			Method exec = source.getClass().getMethod(action, ActionEvent.class);
			if (exec != null) {
				if (widget instanceof ActionableWidget) {
					ActionableWidget actionableWidget = (ActionableWidget) widget;
					EventHandler<ActionEvent> handler = actionableWidget.getOnAction();
					actionableWidget.setOnAction(new ReflectiveActionEventHandler(exec, source, handler));
//				} else {
//					EventHandler<MouseEvent> handler = (EventHandler<MouseEvent>) widget.getOnMouseExited();
//					widget.setOnMouseExited(new ReflectiveMouseEventHandler(exec, applicationPage, handler));
//				}
				}
			}
		}
	}
}
