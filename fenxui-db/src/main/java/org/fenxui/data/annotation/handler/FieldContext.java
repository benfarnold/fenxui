package org.fenxui.data.annotation.handler;

import org.fenxui.application.data.bind.DataBindable;

public class FieldContext {
	private ValueManipulatorReference sourceValueManipulatorReference;
	private DataBindable dataBindable;

	public ValueManipulatorReference getSourceValueManipulatorReference() {
		return sourceValueManipulatorReference;
	}

	public void setSourceValueManipulatorReference(ValueManipulatorReference sourceValueManipulatorReference) {
		this.sourceValueManipulatorReference = sourceValueManipulatorReference;
	}

	public DataBindable getDataBindable() {
		return dataBindable;
	}

	public void setDataBindable(DataBindable dataBindable) {
		this.dataBindable = dataBindable;
	}

}
