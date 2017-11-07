package org.fenxui.application.view.factory.ootb;

import java.util.HashMap;
import java.util.Map;
import org.fenxui.annotation.BindProperty;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.FormFieldButton;
import org.fenxui.application.view.factory.handler.AnnotationHandler;
import org.fenxui.application.view.factory.handler.BindPropertyAnnotationHandler;
import org.fenxui.application.view.factory.handler.FitWidthAnnotationHandler;
import org.fenxui.application.view.factory.handler.FormFieldAnnotationHandler;
import org.fenxui.application.view.factory.handler.FormFieldButtonAnnotationHandler;
import org.fenxui.annotation.FitWidth;

public class DefaultPageFactory extends AbstractPageFactory {

	private static final Map<Class, AnnotationHandler> annotationHandlers = new HashMap<>();

	static {//if introducing custom annotation, make sure you register the handlers here
		annotationHandlers.put(BindProperty.class, new BindPropertyAnnotationHandler());
		annotationHandlers.put(FormField.class, new FormFieldAnnotationHandler());
		annotationHandlers.put(FormFieldButton.class, new FormFieldButtonAnnotationHandler());
		annotationHandlers.put(FitWidth.class, new FitWidthAnnotationHandler());
	}

	public DefaultPageFactory() {
		super(new DefaultPageTitleFactory(), new DefaultFormGridFactory(annotationHandlers), new DefaultFormActionFactory());
	}

}
