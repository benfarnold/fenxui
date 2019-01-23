package org.fenxui.samples.simple;

import javafx.application.Platform;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fenxui.application.FenxuiApplication;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.prototype.FenxuiPrototype;
import org.fenxui.ootb.jfx.jdk8.JFX8Prototype;
import org.fenxui.samples.simple.viewmodel.SampleViewModel;

public class SampleApp extends FenxuiApplication {
	private static final Log log = LogFactory.getLog(SampleApp.class);
	
	@Override
	public FenxuiConfig getFenxuiConfig() {
		return new FenxuiConfig.Builder()
				.css(SampleApp.class.getResource("/css/SampleApp.css"))
				.title("Sample Fenxui App")
				.build();
	}

	@Override
	public FenxuiPrototype getFenxuiPrototype() {
		return JFX8Prototype.newInstance(new SampleViewModel(), () -> {
			log.info("Application closing");
			Platform.exit();
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
