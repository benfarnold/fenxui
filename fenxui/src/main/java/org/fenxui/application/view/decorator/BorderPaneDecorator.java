package org.fenxui.application.view.decorator;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.components.FormContainer;
import org.fenxui.application.view.components.menu.NavigableMenu;
import org.fenxui.application.view.components.menu.PageLink;
import org.fenxui.application.view.components.menu.SideMenu;
import org.fenxui.application.view.factory.ootb.AppConstruction;
import org.fenxui.application.view.factory.ootb.AppFactory;

public class BorderPaneDecorator implements ComponentDecorator<BorderPane, FenxuiViewModel> {

	private final AppFactory appFactory;

	public BorderPaneDecorator(AppFactory appFactory) {
		this.appFactory = appFactory;
	}
	
	@Override
	public BorderPane decorate(FenxuiViewModel viewModel, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {
		AppConstruction appConstruction = appFactory.makeApp(viewModel, fenxuiConfig);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(2));
		FormContainer formContainer = new FormContainer();
		
		NavigableMenu menu;
		switch (appConstruction.getMenuOrientation()) {
			case VERTICAL: 
				menu = new SideMenu(formContainer, appConstruction.getMenuCssClass());
		
				menu.minWidthProperty().set(185);
				borderPane.setLeft(menu);
				borderPane.setCenter(formContainer);
				break;
			case HORIZONTAL: //TODO
			default: //TODO
				menu = null;
		}
		
		List<PageLink> menuList = appConstruction.getMenuItems();
		if (menuList != null) {
			menu.addLinks(menuList);
		}
		return borderPane;
	}

	@Override
	public AppFactory getAppFactory() {
		return appFactory;
	}
}
