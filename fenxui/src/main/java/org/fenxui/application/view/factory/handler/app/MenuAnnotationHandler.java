package org.fenxui.application.view.factory.handler.app;

import java.lang.annotation.Annotation;
import org.fenxui.annotation.app.Menu;
import org.fenxui.application.view.factory.ootb.AppConstruction;

public class MenuAnnotationHandler implements AppAnnotationHandler {

	@Override
	public void handle(AppConstruction appContruction, Annotation annotation) throws IllegalAccessException, NoSuchMethodException {
		Menu menu = (Menu) annotation;
		appContruction.setMenuCssClass(menu.cssClass());
	}

}
