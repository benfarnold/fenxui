package org.fenxui.samples.action;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import org.apache.commons.lang3.StringUtils;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormAction;
import org.fenxui.annotation.FormField;
import org.fenxui.application.view.FenxuiViewModel;

@AppPage("Action Demo Client")
public class ClientViewModel extends FenxuiViewModel implements Marshallable {

	@FormField(label = "Data")
	private StringProperty data = new SimpleStringProperty();

	@FormField(label="reversed", readOnly=true)
	private StringProperty reversed = new SimpleStringProperty();

	@FormAction("Reverse")
	public void reverse(Event event) {
		reversed.set(StringUtils.reverse(data.get()));
	}

	//generated getters/setters
	@Override
	public String getData() {
		return data.get();
	}

	public StringProperty dataProperty() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data.set(data);
	}
}
