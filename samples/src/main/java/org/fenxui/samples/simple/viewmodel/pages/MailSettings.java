package org.fenxui.samples.simple.viewmodel.pages;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.CheckBoxValue;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.Validator;
import org.fenxui.application.view.components.option.ValidatorOptions;
import org.fenxui.samples.simple.viewmodel.SampleViewModel;

import static org.fenxui.application.view.factory.FactoryInitContext.FieldPrototype.CHECKBOX_FIELD;

@AppPage("Mail Settings")
public class MailSettings {
	private SampleViewModel viewModel;
	
	@FormField(label = "Enable notifications", factory=CHECKBOX_FIELD)
	@CheckBoxValue(checked="true", unchecked="false")
	private final StringProperty enableNotifications = new SimpleStringProperty("false");
	
	@FormField(label = "From email")
	@Validator(type = ValidatorOptions.REQUIRED, message = "From email is required", evalExpression="#{enableNotifications} eq 'true'")
	private final StringProperty senderAddress = new SimpleStringProperty();
	
	@FormField(label = "From name")
	@Validator(type = ValidatorOptions.REQUIRED, message = "From name is required", evalExpression="#{enableNotifications} eq 'true'")
	private final StringProperty senderName = new SimpleStringProperty();
	
	public MailSettings(SampleViewModel viewModel) {
		this.viewModel = viewModel;
	}
	
	public String getSenderAddress() {
		return senderAddress.get();
	}

	public StringProperty senderAddressProperty() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress.set(senderAddress);
	}

	public String getSenderName() {
		return senderName.get();
	}

	public StringProperty senderNameProperty() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName.set(senderName);
	}

	public String getEnableNotifications() {
		return enableNotifications.get();
	}

	public StringProperty enableNotificationsProperty() {
		return enableNotifications;
	}

	public void setEnableNotifications(String enableNotifications) {
		this.enableNotifications.set(enableNotifications);
	}
}
