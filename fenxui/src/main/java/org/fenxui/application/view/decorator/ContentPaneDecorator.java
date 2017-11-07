package org.fenxui.application.view.decorator;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.bind.ContentPane;
import org.fenxui.application.view.factory.ootb.AbstractPageFactory;

/**
 * Wraps one or more @AppPage
 */
public class ContentPaneDecorator implements ComponentDecorator<ContentPane, Object> {

	private AbstractPageFactory pageFactory;

	public ContentPaneDecorator(AbstractPageFactory pageFactory) {
		this.pageFactory = pageFactory;
	}

	@Override
	public ContentPane decorate(Object applicationPage, FenxuiConfig fenxuiConfig) {
		ContentPane gridPane = pageFactory.makePage(applicationPage, fenxuiConfig);
		gridPane.setAlignment(fenxuiConfig.getAlignent());
//		gridPane.setHgap(fenxuiConfig.getHgap());
//		gridPane.setVgap(fenxuiConfig.getVgap());
		gridPane.setPadding(fenxuiConfig.getPadding());
		gridPane.autosize();
		return gridPane;
	}
}
