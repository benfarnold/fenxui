package org.fenxui.data.annotation.handler;

import java.lang.annotation.Annotation;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.data.annotation.AutoLoad;

public class AutoLoadAnnotationHandler implements PageAnnotationHandler {

	@Override
	public void handle(PageContext pageContext, Annotation annotation) {
		pageContext.setAutoload(((AutoLoad) annotation).autoLoad());
	}

}
