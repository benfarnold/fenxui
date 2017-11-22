package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import org.fenxui.annotation.ColSpan;

public class ColSpanAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException {
		ColSpan colSpan = (ColSpan) annotation;
		fieldContext.getRowContext().setColSpan(colSpan.value());
	}

}
