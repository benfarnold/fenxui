package org.fenxui.samples.action;

public class ClientDataModel implements Marshallable {
	private String data;

	//generated getters/setters
	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}
}
