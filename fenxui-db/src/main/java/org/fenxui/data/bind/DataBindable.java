package org.fenxui.data.bind;

public interface DataBindable<T> {

	public T getValue();

	public void setValue(T value);

}
