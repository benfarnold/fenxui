package org.fenxui.samples.networth.ui;

import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.api.option.layout.Orientation;
import org.fenxui.samples.networth.ui.liabilities.CurrentLiabilitiesPage;
import org.fenxui.samples.networth.ui.liabilities.LongTermLiabilitiesPage;

@Menu(orientation = Orientation.HORIZONTAL)
public class Liabilities {

	@MenuItem("Current liabilities")
	private CurrentLiabilitiesPage currentLiabilitiesPage = new CurrentLiabilitiesPage();

	@MenuItem("Long-term liabilities")
	private LongTermLiabilitiesPage longTermLiabilitiesPage = new LongTermLiabilitiesPage();
}
