package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import org.fenxui.application.exception.FenxuiInitializationException;

public interface FieldAnnotationHandler {

	void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException;
}
