package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;

public interface AnnotationHandler {

	public void handle(NodeContext fieldContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException;
}
