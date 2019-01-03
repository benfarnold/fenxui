package org.fenxui.application.view.factory.handler.app;

import java.lang.annotation.Annotation;
import java.util.List;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.application.view.components.NamedHideable;
import org.fenxui.application.view.components.menu.PageLink;
import org.fenxui.application.view.factory.ootb.AppConstruction;

/**
 * Handle MenuItem annotations
 */
public class MenuItemAnnotationHandler implements AppAnnotationHandler {
	@Override
	public void handle(AppConstruction appContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException {
		MenuItem menuItem = (MenuItem) annotation;
		List<NamedHideable> items = appContext.getContentPanes();
		NamedHideable page = items.get(items.size() - 1);//last one would be the in-process page
		PageLink pageLink = new PageLink(menuItem.value(),  page, menuItem.required());
		appContext.addMenuItem(pageLink);
//		Label label = new Label(menuItem.value());
//		label.getStyleClass().add(menuItem.cssClass());
//		appContext.addPostProcessor(new PostProcessor() {
//			@Override
//			public void postProcess(Node node) throws FenxuiInitializationException {
//				label.setOnMouseClicked((av) -> {
//					appContext.getContentPanes().forEach(pane -> pane.setVisible(false));
//					node.setVisible(true);
//				});
//			}
//		});
//		appContext.addMenuItem(label);
	}

}
