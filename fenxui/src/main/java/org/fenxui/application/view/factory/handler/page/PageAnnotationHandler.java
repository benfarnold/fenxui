package org.fenxui.application.view.factory.handler.page;

import java.lang.annotation.Annotation;

public interface PageAnnotationHandler {

	void handle(PageContext pageContext, Annotation annotation);
}
