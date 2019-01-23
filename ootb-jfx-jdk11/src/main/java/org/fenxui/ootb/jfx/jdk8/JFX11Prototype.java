package org.fenxui.ootb.jfx.jdk8;

import javafx.stage.Stage;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.decorator.FactoryInvocation;
import org.fenxui.application.view.decorator.SceneDecorator;
import org.fenxui.application.view.factory.FenxuiFactory;
import org.fenxui.application.view.factory.ootb.AppFactory;
import org.fenxui.application.view.factory.ootb.DefaultAppFactory;
import org.fenxui.application.view.prototype.FenxuiPrototype;
import org.fenxui.ootb.jfx.jdk8.factory.JFX11FactoryInitContext;
import org.fenxui.ootb.jfx.layout.JFXWindowDressingDecorator;

/**
 * Prototype for a page styled with JFoenix library
 */
public interface JFX11Prototype extends FenxuiPrototype {

	static FenxuiPrototype newInstance(FenxuiViewModel fenxuiViewModel, Runnable onCloseAction) {
		return newInstance(fenxuiViewModel, new DefaultAppFactory(onCloseAction, new JFX11FactoryInitContext()));
	}

	static FenxuiPrototype newInstance(FenxuiViewModel fenxuiViewModel, AppFactory appFactory) {
		return (FenxuiConfig fenxuiConfig) -> {
			appFactory.setViewModel(fenxuiViewModel);
			SceneDecorator sceneDecorator = new SceneDecorator(new JFXWindowDressingDecorator(new FactoryInvocation(appFactory)));
			return (FenxuiFactory) (Stage stage) -> {
				fenxuiViewModel.setStage(stage);
				return sceneDecorator.decorate(fenxuiConfig);
			};
		};
	}

}
