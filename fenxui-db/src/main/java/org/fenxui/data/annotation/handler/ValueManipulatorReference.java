package org.fenxui.data.annotation.handler;

import javafx.scene.Node;
import org.fenxui.application.data.bind.DataBindable;

public class ValueManipulatorReference {

	private final DataBindable node;
	private final ValueManipulatorReferenceMethods uiMethods;
	private final ValueManipulatorReferenceMethods dbMethods;

	public ValueManipulatorReference(DataBindable node, ValueManipulatorReferenceMethods uiMethods, ValueManipulatorReferenceMethods dbMethods) {
		this.node = node;
		this.uiMethods = uiMethods;
		this.dbMethods = dbMethods;
	}

	public ValueManipulatorReferenceMethods getUiMethods() {
		return uiMethods;
	}

	public ValueManipulatorReferenceMethods getDbMethods() {
		return dbMethods;
	}

	public DataBindable getNode() {
		return node;
	}

}
