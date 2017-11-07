package org.fenxui.application.view.factory.handler;

import java.lang.reflect.InvocationTargetException;
import javafx.scene.Node;

@FunctionalInterface
public interface PostProcessor {

	void postProcess(Node node) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
