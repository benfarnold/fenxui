package org.fenxui.samples.simple.viewmodel.pages;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.Validator;
import org.fenxui.annotation.ValueProviderValue;
import org.fenxui.application.view.components.option.ValidatorOptions;
import org.fenxui.samples.simple.viewmodel.SampleViewModel;

import static org.fenxui.application.view.factory.FactoryInitContext.FieldPrototype.SELECT_FIELD;
import static org.fenxui.application.view.factory.FactoryInitContext.FieldPrototype.PASSWORD_FIELD;

@AppPage("Database Settings")
public class DatabaseSettings {
	private SampleViewModel viewModel;
	
	@FormField(label = "Server Type", factory = SELECT_FIELD)
	@Validator(type = ValidatorOptions.REQUIRED, message = "Server type is required")
	@ValueProviderValue(key="ORCL", value= "Oracle")
	private final StringProperty serverType = new SimpleStringProperty();
	
	@FormField(label = "Server")
	@Validator(type = ValidatorOptions.REQUIRED, message = "Server is required")
	private final StringProperty server = new SimpleStringProperty();
	
	@FormField(label = "Port")
	@Validator(type = ValidatorOptions.REQUIRED, message = "Port is required")
	@Validator(type = ValidatorOptions.NUMERIC, message = "Port must be numeric")
	private final StringProperty port = new SimpleStringProperty();
	
	@FormField(label = "SID")
	@Validator(type = ValidatorOptions.REQUIRED, message = "SID is required")
	private final StringProperty sid = new SimpleStringProperty();
	
	@FormField(label = "Username")
	private final StringProperty username = new SimpleStringProperty();

	@FormField(label = "Password", factory=PASSWORD_FIELD)
	private final StringProperty password = new SimpleStringProperty();

	public DatabaseSettings(SampleViewModel viewModel) {
		this.viewModel = viewModel;
	}
	
	public String getServerType() {
		return serverType.get();
	}

	public StringProperty serverTypeProperty() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType.set(serverType);
	}

	public String getServer() {
		return server.get();
	}

	public StringProperty serverProperty() {
		return server;
	}

	public void setServer(String server) {
		this.server.set(server);
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

	public String getSid() {
		return sid.get();
	}

	public StringProperty sidProperty() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid.set(sid);
	}

	public String getUsername() {
		return username.get();
	}

	public StringProperty usernameProperty() {
		return username;
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public String getPassword() {
		return password.get();
	}

	public StringProperty passwordProperty() {
		return password;
	}

	public void setPassword(String password) {
		this.password.set(password);
	}
	

}
