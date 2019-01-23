package org.fenxui.samples.networth.ui;


import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.view.FenxuiViewModel;

@Menu
public class NetWorthViewModel extends FenxuiViewModel {
	@MenuItem("Assets")
	private final Assets assets = new Assets();

	@MenuItem("Liabilities")
	private final Liabilities liabilities = new Liabilities();

	@MenuItem("Summary")
	private final Summary summary = new Summary();

	public Assets getAssets() {
		return assets;
	}

	public Liabilities getLiabilities() {
		return liabilities;
	}

//	public Summary getSummary() {
//		return summary;
//	}
}
