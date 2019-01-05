package org.fenxui.samples.viewmodel.pages;

import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.view.factory.handler.app.Orientation;
import org.fenxui.samples.viewmodel.SampleViewModel;

@Menu(orientation = Orientation.HORIZONTAL)
public class TabbedSettings {
	private final SampleViewModel sampleViewModel;

	@MenuItem("Tab A")
	private final SimpleTabPageA simplePageA = new SimpleTabPageA(this);

	@MenuItem("Tab B")
	private final SimpleTabPageB simplePageB = new SimpleTabPageB(this);

	public TabbedSettings(SampleViewModel sampleViewModel) {
		this.sampleViewModel = sampleViewModel;
	}

	public SimpleTabPageA getSimplePageA() {
		return simplePageA;
	}

	public SimpleTabPageB getSimplePageB() {
		return simplePageB;
	}
}
