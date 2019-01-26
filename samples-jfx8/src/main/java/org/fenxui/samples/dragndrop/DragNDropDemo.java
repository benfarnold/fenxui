package org.fenxui.samples.dragndrop;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.application.FenxuiApplication;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.decorator.FactoryInvocation;
import org.fenxui.application.view.decorator.SceneDecorator;
import org.fenxui.application.view.factory.FenxuiFactory;
import org.fenxui.application.view.prototype.FenxuiPrototype;
import org.fenxui.ootb.jfx.jdk8.factory.JFX8FactoryInitContext;
import org.fenxui.ootb.jfx.layout.JFXWindowDressingDecorator;
import org.fenxui.samples.dragndrop.custom.*;
import org.fenxui.samples.dragndrop.view.DragNDropViewModel;

import java.io.File;

public class DragNDropDemo extends FenxuiApplication {
	private static final Logger logger = LogManager.getLogger(DragNDropDemo.class);

	@Override
	public FenxuiConfig getFenxuiConfig() {
		logger.info("Init config");
		return new FenxuiConfig.Builder()
				.css(DragNDropDemo.class.getResource("/css/DragNDropDemo.css"))
				.title("Drag and Drop Demo")
				.build();
	}

	@Override
	public FenxuiPrototype getFenxuiPrototype() {
		DragNDropViewModel applicationViewModel = new DragNDropViewModel();
		Runnable onCloseAction = () -> {
			logger.info("Application closing.");
			Platform.exit();
		};
		String userDir = System.getProperty("user.home");
		String rootDir = userDir + File.separator + ".fenxui" + File.separator + "tree";

		StyleableAppFactory appFactory = new StyleableAppFactory(onCloseAction, new JFX8FactoryInitContext());
		TreeFactoryContext treeFactoryContext = new TreeFactoryContext();
		treeFactoryContext.setStrategy(new TreeFactoryYamlFileStrategy());
		appFactory.set(LayoutDecorator.TargetRegion.LEFT, new FSTree(rootDir));

		return (FenxuiConfig fenxuiConfig) -> {
			appFactory.setViewModel(applicationViewModel);

			SceneDecorator sceneDecorator = new SceneDecorator(new JFXWindowDressingDecorator(new LayoutDecorator(new FactoryInvocation(appFactory))));
			return (FenxuiFactory) (Stage stage) -> {
				applicationViewModel.setStage(stage);
				return sceneDecorator.decorate(fenxuiConfig);
			};
		};
	}
}
