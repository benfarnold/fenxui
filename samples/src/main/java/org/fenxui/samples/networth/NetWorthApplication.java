package org.fenxui.samples.networth;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.application.FenxuiApplication;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.prototype.FenxuiPrototype;
import org.fenxui.application.view.prototype.JFoenixPrototype;
import org.fenxui.samples.common.data.AppModelOptions;
import org.fenxui.samples.common.data.DataService;
import org.fenxui.samples.networth.data.DemoDataModel;
import org.fenxui.samples.networth.ui.NetWorthViewModel;

public class NetWorthApplication extends FenxuiApplication {
	private static final Logger logger = LogManager.getLogger(NetWorthApplication.class);

	@Override
	public FenxuiConfig getFenxuiConfig() {
		logger.info("Init config");
		return new FenxuiConfig.Builder()
				.css(NetWorthApplication.class.getResource("/css/NetWorthApplication.css"))
				.title("Net Worth Application")
				.build();
	}

	@Override
	public FenxuiPrototype getFenxuiPrototype() {
		NetWorthViewModel applicationViewModel = new NetWorthViewModel();

		try {
			DataService.INSTANCE.setAppModelOptions(
					new AppModelOptions("NetWorthApp.json", new TypeReference<DemoDataModel>() {}, () -> new DemoDataModel()));
			DemoDataModel demoDataModel = DataService.INSTANCE.loadDataFromDisk();

			return JFoenixPrototype.newInstance(applicationViewModel, () -> {
				logger.info("Application closing.  Auto-saving data.");
				try {
					DataService.INSTANCE.saveDataModel(demoDataModel);
				} catch (FenxuiInitializationException ex) {
					logger.error("Error saving", ex);
				}
				Platform.exit();
			});
		} catch (FenxuiInitializationException ex) {
			logger.error("Error opening database", ex);
		}
		return null;

	}
}
