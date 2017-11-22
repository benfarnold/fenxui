package org.fenxui.application.view.factory.handler.page;

import java.lang.annotation.Annotation;
import javafx.scene.control.Label;
import org.fenxui.annotation.AppPage;

public class AppPageAnnotationHandler implements PageAnnotationHandler {

	@Override
	public void handle(PageContext pageContext, Annotation annotation) {
		AppPage appPage = (AppPage) annotation;
		Label label = new Label(appPage.value());
		label.setId(appPage.cssClass() + "-title");
		pageContext.setTitle(label);
	}

}
