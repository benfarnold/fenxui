package org.fenxui.samples.viewmodel;

import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.data.mongo.annotation.EnableMongo;
import org.fenxui.samples.viewmodel.pages.PageOne;
import org.fenxui.samples.viewmodel.pages.PageTwo;

@Menu
@EnableMongo(name = "sampleapp-mongo.db")
public class SampleViewModel extends FenxuiViewModel {

	@MenuItem("Welcome")
	private final PageOne pageOne = new PageOne(this);

	@MenuItem("Show Me")
	private final PageTwo pageTwo = new PageTwo(this);

//	@GlobalBind("enable")
//	@MenuItem("Not Unlocked Yet")
//	private final PageThree pageThree = new PageThree(this);
	public PageOne getPageOne() {
		return pageOne;
	}

	public PageTwo getPageTwo() {
		return pageTwo;
	}
}
