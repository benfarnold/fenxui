package org.fenxui.samples.simple.viewmodel.pages;

import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.api.option.layout.Orientation;

@Menu(orientation = Orientation.HORIZONTAL)
public class TabbedSettings {

	@MenuItem(value = "Tab A", cssClass = "tabpage-link")
	private final SimpleTabPageA simplePageA = new SimpleTabPageA(this);

	@MenuItem(value = "Tab B", cssClass = "tabpage-link")
	private final SimpleTabPageB simplePageB = new SimpleTabPageB(this);

	public SimpleTabPageA getSimplePageA() {
		return simplePageA;
	}

	public SimpleTabPageB getSimplePageB() {
		return simplePageB;
	}
}
