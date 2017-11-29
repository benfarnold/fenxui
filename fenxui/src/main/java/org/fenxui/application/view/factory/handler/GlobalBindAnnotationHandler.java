package org.fenxui.application.view.factory.handler;

import java.lang.annotation.Annotation;
import java.util.StringJoiner;
import javafx.beans.property.Property;
import javafx.scene.Node;
import org.fenxui.annotation.GlobalBind;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.state.GlobalHive;
import org.fenxui.application.view.factory.handler.util.ReflectivePropertyRetriever;

public class GlobalBindAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		GlobalBind bindProperty = (GlobalBind) annotation;
		String fieldProperty = bindProperty.value();
		String fieldName = fieldContext.getField().getName();
		String className = fieldContext.getSource().getClass().getName();
		String globalId = new StringJoiner("_")
				.add(className)
				.add(fieldName)
				.add(fieldProperty).toString();

		fieldContext.addPostProcessor(new PostProcessor() {
					@Override
					public void postProcess(Node node) throws FenxuiInitializationException {
						ReflectivePropertyRetriever observingRetriever = new ReflectivePropertyRetriever(fieldProperty);
						Property observingProperty = observingRetriever.getProperty(node);
						GlobalHive.INSTANCE.bind(globalId, observingProperty);
					}
				});
	}

}
