package org.fenxui.application.view.decorator;

import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.factory.ootb.AppConstruction;
import org.fenxui.application.view.factory.ootb.AppFactory;

import java.util.List;

/**
 * Two-frame layout. Menu on left, app-pages on right.
 */
public class SplitPaneDecorator implements ComponentDecorator<SplitPane, FenxuiViewModel> {

	private final AppFactory appFactory;

	public SplitPaneDecorator(AppFactory appFactory) {
		this.appFactory = appFactory;
	}

	@Override
	public SplitPane decorate(FenxuiViewModel viewModel, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {
		AppConstruction appConstruction = appFactory.makeApp(viewModel, fenxuiConfig);
		GridPane leftMenu = new GridPane();
		leftMenu.setAlignment(fenxuiConfig.getAlignent());
		leftMenu.setHgap(fenxuiConfig.getHgap());
		leftMenu.setVgap(fenxuiConfig.getVgap());
		leftMenu.setPadding(fenxuiConfig.getPadding());
		leftMenu.getStyleClass().add(appConstruction.getMenuCssClass());
		List<Node> menuList = appConstruction.getMenuItems();
		for (int i = 0; i< menuList.size(); i++) {
			leftMenu.add(menuList.get(i), 0, i);
		}
		StackPane mainPain = new StackPane();

		SplitPane splitPane = new SplitPane(leftMenu, mainPain);
		splitPane.setDividerPosition(0, .3);
		splitPane.getStyleClass().add(appConstruction.getMenuCssClass());

		mainPain.getChildren().addAll(appConstruction.getContentPanes());

		return splitPane;
	}

}
