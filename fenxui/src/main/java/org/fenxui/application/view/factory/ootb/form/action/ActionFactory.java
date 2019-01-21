package org.fenxui.application.view.factory.ootb.form.action;

import javafx.scene.Node;
import org.fenxui.application.view.factory.handler.action.MethodOption;

public interface ActionFactory {
	Node create(MethodOption option);
}
