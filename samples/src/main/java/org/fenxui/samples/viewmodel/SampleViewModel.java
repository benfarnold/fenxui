package org.fenxui.samples.viewmodel;

import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.samples.viewmodel.pages.MailSettings;
import org.fenxui.samples.viewmodel.pages.TabbedSettings;
import org.fenxui.samples.viewmodel.pages.ServerSettings;
import org.fenxui.samples.viewmodel.pages.DatabaseSettings;

@Menu
public class SampleViewModel extends FenxuiViewModel {

	@MenuItem("Server Settings")
	private final ServerSettings serverSettings = new ServerSettings(this);

	@MenuItem("Database Settings")
	private final DatabaseSettings databaseSettings = new DatabaseSettings(this);

	@MenuItem("Mail Settings")
	private final MailSettings mailSettings = new MailSettings(this);

	@MenuItem("Tabbed Settings")
	private final TabbedSettings tabbedSettings = new TabbedSettings(this);

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
