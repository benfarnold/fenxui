package org.fenxui.samples.viewmodel.pages;

import org.fenxui.annotation.AppPage;
import org.fenxui.samples.viewmodel.SampleViewModel;

@AppPage("Congratulations")
public class PageThree {
	private SampleViewModel viewMode;

	public PageThree(SampleViewModel aThis) {
		this.viewMode = aThis;
	}

}
