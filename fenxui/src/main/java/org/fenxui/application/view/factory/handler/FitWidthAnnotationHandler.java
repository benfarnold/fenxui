package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import org.fenxui.annotation.FitWidth;
import org.fenxui.application.exception.FenxuiInitializationException;

public class FitWidthAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		FitWidth fitWidth = (FitWidth) annotation;
		PostProcessor fitWidthPostProcessor = (Node node) -> {
			if (node instanceof ImageView) {
				ImageView view = (ImageView) node;
				view.setFitWidth(fitWidth.value());
				view.setPreserveRatio(true);
			}
		};
		fieldContext.addPostProcessor(fitWidthPostProcessor);
	}

}
