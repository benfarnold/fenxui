package org.fenxui.samples.networth.ui;

import org.fenxui.samples.networth.ui.assets.CashAssetsPage;
import org.fenxui.samples.networth.ui.assets.InvestedAssetsPage;
import org.fenxui.samples.networth.ui.assets.UseAssetsPage;
import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.view.factory.handler.app.Orientation;

@Menu(orientation = Orientation.HORIZONTAL)
public class Assets {

	@MenuItem("Cash, etc")
	private final CashAssetsPage cashAssetsPage = new CashAssetsPage();

	@MenuItem("Investments")
	private final InvestedAssetsPage investedAssetsPage = new InvestedAssetsPage();

	@MenuItem("Use Assets")
	private final UseAssetsPage useAssetsPage = new UseAssetsPage();
}
