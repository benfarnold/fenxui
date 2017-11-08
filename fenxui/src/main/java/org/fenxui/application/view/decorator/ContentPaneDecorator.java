package org.fenxui.application.view.decorator;

import javafx.geometry.Pos;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.bind.ContentPane;
import org.fenxui.application.view.factory.ootb.AbstractPageFactory;

/**
 * Wraps one or more @AppPage
 */
public class ContentPaneDecorator implements ComponentDecorator<ContentPane, Object> {

	private final AbstractPageFactory pageFactory;

	public ContentPaneDecorator(AbstractPageFactory pageFactory) {
		this.pageFactory = pageFactory;
	}

	@Override
	public ContentPane decorate(Object applicationPage, FenxuiConfig fenxuiConfig) {
		ContentPane gridPane = pageFactory.makePage(applicationPage, fenxuiConfig);
		gridPane.getStyleClass().add("contentPane");
		gridPane.setAlignment(Pos.CENTER_LEFT);
//		gridPane.setHgap(fenxuiConfig.getHgap());
//		gridPane.setVgap(fenxuiConfig.getVgap());
		gridPane.setPadding(fenxuiConfig.getPadding());
//		gridPane.autosize();
		return gridPane;
	}
}
