package org.fenxui.samples.networth;

import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.application.FenxuiApplication;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.prototype.FenxuiPrototype;
import org.fenxui.ootb.jfx.jdk8.JFX11Prototype;
import org.fenxui.samples.networth.data.DataService;
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
		DemoDataModel demoDataModel = DataService.INSTANCE.loadDataFromDisk();
//		applicationViewModel.setText(demoDataModel.getText());

		return JFX11Prototype.newInstance(applicationViewModel, () -> {
			logger.info("Application closing.  Auto-saving data.");
//			demoDataModel.setText(applicationViewModel.getText());
			DataService.INSTANCE.saveDataModel(demoDataModel);
			Platform.exit();
		});
	}
}
