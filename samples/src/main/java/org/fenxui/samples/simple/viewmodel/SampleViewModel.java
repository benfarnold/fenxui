package org.fenxui.samples.simple.viewmodel;

import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.samples.simple.viewmodel.pages.MailSettings;
import org.fenxui.samples.simple.viewmodel.pages.TabbedSettings;
import org.fenxui.samples.simple.viewmodel.pages.ServerSettings;
import org.fenxui.samples.simple.viewmodel.pages.DatabaseSettings;

@Menu
public class SampleViewModel extends FenxuiViewModel {

	@MenuItem("Server Settings")
	private final ServerSettings serverSettings = new ServerSettings(this);

	@MenuItem("Database Settings")
	private final DatabaseSettings databaseSettings = new DatabaseSettings(this);

	@MenuItem("Mail Settings")
	private final MailSettings mailSettings = new MailSettings(this);

	@MenuItem("Tabbed Settings")
	private final TabbedSettings tabbedSettings = new TabbedSettings();

	public ServerSettings getServerSettings() {
		return serverSettings;
	}

	public DatabaseSettings getDatabaseSettings() {
		return databaseSettings;
	}

	public MailSettings getMailSettings() {
		return mailSettings;
	}

	public TabbedSettings getTabbedSettings() {
		return tabbedSettings;
	}
}
