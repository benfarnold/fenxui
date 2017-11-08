package org.fenxui.samples;

import org.fenxui.application.FenxuiApplication;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.prototype.FenxuiPrototype;
import org.fenxui.application.view.prototype.MenuAppPrototype;
import org.fenxui.samples.viewmodel.SampleViewModel;

public class SampleApp extends FenxuiApplication {

	@Override
	public FenxuiConfig getFenxuiConfig() {
		return new FenxuiConfig.Builder()
				.css(SampleApp.class.getResource("/SampleApp.css"))
				.build();
	}

	@Override
	public FenxuiPrototype getFenxuiPrototype() {
		return MenuAppPrototype.newInstance(new SampleViewModel());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
