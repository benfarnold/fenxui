package org.fenxui.samples.action;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.application.FenxuiApplication;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.prototype.FenxuiPrototype;
import org.fenxui.ootb.jfx.jdk8.JFX8Prototype;

public class ButtonActionApp extends FenxuiApplication {
	private static final Logger logger = LogManager.getLogger(ButtonActionApp.class);

	@Override
	public FenxuiConfig getFenxuiConfig() {
		logger.info("Init config");
		return new FenxuiConfig.Builder()
				.css(ButtonActionApp.class.getResource("/css/ButtonActionApp.css"))
				.title("Demo Application")
				.initialSceneWidth(350)
				.initialSceneHeight(200)
				.build();
	}

	@Override
	public FenxuiPrototype getFenxuiPrototype() {
		ClientViewModel applicationViewModel = new ClientViewModel();

		//below commented-out code demonstrates how to read/persist data model
//		DataService.INSTANCE.setAppModelOptions(
//				new AppModelOptions("ClientDataModel.json", new TypeReference<ClientDataModel>() {}, () -> new ClientDataModel()));
//		try {
//			ClientDataModel clientDataModel = DataService.INSTANCE.loadDataFromDisk();
//			clientDataModel.marshallTo(applicationViewModel);

			return JFX8Prototype.newInstance(applicationViewModel, () -> {
				logger.info("Application closing.  Auto-saving data.");
//				applicationViewModel.marshallTo(clientDataModel);
//				try {
//					DataService.INSTANCE.saveDataModel(clientDataModel);
//				} catch (FenxuiInitializationException e) {
//					logger.error("Error saving database", e);
//				}
				Platform.exit();
			});
//		} catch (FenxuiInitializationException e) {
//			logger.error("Error loading database", e);
//		}
//		return null;
	}

}
