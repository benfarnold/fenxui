package org.fenxui.application.view.factory.handler.action;

import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.factory.handler.NodeContext;

import java.lang.annotation.Annotation;

public interface MethodAnnotationHandler {

	void handle(NodeContext nodeContext, Annotation annotation) throws FenxuiInitializationException;
}
