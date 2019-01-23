package org.fenxui.samples.action;

public interface Marshallable {
	String getData();
	void setData(String data);

	default void marshallTo(Marshallable target) {
		target.setData(getData());
	}
}
