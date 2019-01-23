package org.fenxui.samples.simple.viewmodel.pages;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormAction;
import org.fenxui.api.prototype.OOTBFieldValidators;
import org.fenxui.samples.simple.viewmodel.SampleViewModel;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.Validator;

@AppPage("Server Settings")
public class ServerSettings {
	private SampleViewModel viewModel;

	@FormField(label = "Server")
	@Validator(type = OOTBFieldValidators.REQUIRED, message = "Server is required")
	private final StringProperty machine = new SimpleStringProperty();
	
	@FormField(label = "Port")
	@Validator(type = OOTBFieldValidators.REQUIRED, message = "Port is required")
	@Validator(type = OOTBFieldValidators.NUMERIC, message = "Port must be numeric")
	private final StringProperty port = new SimpleStringProperty("8080");

	public ServerSettings(SampleViewModel viewModel) {
		this.viewModel = viewModel;
	}

	@FormAction("Save")
	public void doSave(ActionEvent actionEvent) {

	}

	public String getMachine() {
		return machine.get();
	}

	public StringProperty machineProperty() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine.set(machine);
	}

	public String getPort() {
		return port.get();
	}

	public StringProperty portProperty() {
		return port;
	}

	public void setPort(String port) {
		this.port.set(port);
	}
}
