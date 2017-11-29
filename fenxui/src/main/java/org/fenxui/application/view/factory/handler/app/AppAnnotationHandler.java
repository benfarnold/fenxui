package org.fenxui.application.view.factory.handler.app;

import java.lang.annotation.Annotation;
import org.fenxui.application.view.factory.ootb.AppConstruction;

/**
 * Handle annotations that effect the entire application
 */
public interface AppAnnotationHandler {

	void handle(AppConstruction appContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException;
}
