package org.fenxui.samples.bmi;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.application.FenxuiApplication;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.prototype.FenxuiPrototype;
import org.fenxui.ootb.jfx.jdk8.JFX8Prototype;

public class BmiApp extends FenxuiApplication {
	private static final Logger logger = LogManager.getLogger(BmiApp.class);

	@Override
	public FenxuiConfig getFenxuiConfig() {
		logger.info("Init config");
		return new FenxuiConfig.Builder()
				.css(BmiApp.class.getResource("/css/BMI.css"))
				.title("BMI Application")
				.initialSceneHeight(300)
				.initialSceneWidth(300)
				.build();
	}

	@Override
	public FenxuiPrototype getFenxuiPrototype() {
		BMIViewModel applicationViewModel = new BMIViewModel();

		return JFX8Prototype.newInstance(applicationViewModel, () -> {
			logger.info("Application closing.  Auto-saving data.");
			Platform.exit();
		});
	}
}
