package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import org.fenxui.annotation.ColSpan;
import org.fenxui.application.exception.FenxuiInitializationException;

public class ColSpanAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		ColSpan colSpan = (ColSpan) annotation;
		fieldContext.getRowContext().setColSpan(colSpan.value());
	}

}
