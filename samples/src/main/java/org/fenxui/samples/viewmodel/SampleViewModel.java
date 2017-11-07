package org.fenxui.samples.viewmodel;

import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.MenuItem;
import org.fenxui.application.view.MenuAppViewModel;
import org.fenxui.samples.viewmodel.pages.PageOne;
import org.fenxui.samples.viewmodel.pages.PageTwo;

@AppPage(value = "", cssClass = "menupage")
public class SampleViewModel extends MenuAppViewModel {
	@MenuItem("Page 1")
	private final PageOne pageOne = new PageOne(this);

	@MenuItem("Settings")
	private final PageTwo pageTwo = new PageTwo(this);
}
