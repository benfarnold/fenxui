package org.fenxui.data.annotation.handler;

import java.lang.reflect.Method;

public class ValueManipulatorReferenceMethods {

	private final String getterName;
	private final String setterName;
	private Method getterMethod;
	private Method setterMethod;

	public ValueManipulatorReferenceMethods(String getterName, String setterName) {
		this.getterName = getterName;
		this.setterName = setterName;
	}

	public String getGetterName() {
		return getterName;
	}

	public String getSetterName() {
		return setterName;
	}

	public Method getGetterMethod() {
		return getterMethod;
	}

	public void setGetterMethod(Method getterMethod) {
		this.getterMethod = getterMethod;
	}

	public Method getSetterMethod() {
		return setterMethod;
	}

	public void setSetterMethod(Method setterMethod) {
		this.setterMethod = setterMethod;
	}

}
