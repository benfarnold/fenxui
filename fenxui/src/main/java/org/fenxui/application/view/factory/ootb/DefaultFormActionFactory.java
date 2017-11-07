package org.fenxui.application.view.factory.ootb;

import java.lang.reflect.Method;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.fenxui.annotation.FormAction;
import org.fenxui.application.view.action.ReflectiveActionEventHandler;
import org.fenxui.application.view.factory.FormActionFactory;

public class DefaultFormActionFactory implements FormActionFactory {

	@Override
	public HBox makeFormActionRow(Object applicationPage) {
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		for (Method method : applicationPage.getClass().getMethods()) {
			FormAction annotation = method.getAnnotation(FormAction.class);
			if (annotation != null) {
				Button button = new Button(annotation.value());
				button.setOnAction(new ReflectiveActionEventHandler(method, applicationPage, button.getOnAction()));
				hbBtn.getChildren().add(button);
			}
		}
		return hbBtn;
	}

}
