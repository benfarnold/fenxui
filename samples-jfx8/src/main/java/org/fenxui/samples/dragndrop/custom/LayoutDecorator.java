package org.fenxui.samples.dragndrop.custom;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.decorator.AbstractDecorator;
import org.fenxui.application.view.decorator.ComponentDecorator;
import org.fenxui.application.view.decorator.FactoryInvocation;
import org.fenxui.application.view.factory.ootb.AppFactory;
import org.fenxui.core.exception.FenxuiInitializationException;
import org.fenxui.samples.dragndrop.custom.StyleableAppFactory;

public class LayoutDecorator extends AbstractDecorator<Region> implements ComponentDecorator<Region> {

	public LayoutDecorator(ComponentDecorator decorator) {
		super(decorator);
	}

	@Override
	public Region decorate(FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {
		Region mainPane = decorator.decorate(fenxuiConfig);
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(mainPane);
		StyleableAppFactory appFactory = (StyleableAppFactory) getAppFactory();

		for (TargetRegion region : TargetRegion.values()) {
			Node node = appFactory.get(region);
			if (node != null) {
				region.set(borderPane, node);
			}
		}
		return borderPane;
	}

	public enum TargetRegion {
		TOP {
			@Override
			void set(BorderPane borderPane, Node node) {
				borderPane.setTop(node);
			}
		}, BOTTOM {
			@Override
			void set(BorderPane borderPane, Node node) {
				borderPane.setBottom(node);
			}
		}, LEFT {
			@Override
			void set(BorderPane borderPane, Node node) {
				borderPane.setLeft(node);
			}
		}, RIGHT {
			@Override
			void set(BorderPane borderPane, Node node) {
				borderPane.setRight(node);
			}
		};
		abstract void set(BorderPane borderPane, Node node);
	}
}
