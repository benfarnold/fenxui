package org.fenxui.samples.viewmodel;

import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.GlobalBind;
import org.fenxui.annotation.MenuItem;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.samples.viewmodel.pages.PageOne;
import org.fenxui.samples.viewmodel.pages.PageThree;
import org.fenxui.samples.viewmodel.pages.PageTwo;

@AppPage(value = "", cssClass = "menupage")
public class SampleViewModel extends FenxuiViewModel {

	@MenuItem("Welcome")
	private final PageOne pageOne = new PageOne(this);

	@MenuItem("Show Me")
	private final PageTwo pageTwo = new PageTwo(this);

//	@GlobalBind("enable")
//	@MenuItem("Not Unlocked Yet")
//	private final PageThree pageThree = new PageThree(this);
}
