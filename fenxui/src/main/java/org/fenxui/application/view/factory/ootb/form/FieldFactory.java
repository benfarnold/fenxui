package org.fenxui.application.view.factory.ootb.form;

import javafx.scene.Node;
import org.fenxui.application.view.components.option.FieldOption;

public interface FieldFactory {
	Node create(FieldOption option);
}
