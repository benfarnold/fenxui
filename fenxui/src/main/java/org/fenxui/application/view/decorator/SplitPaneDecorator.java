package org.fenxui.application.view.decorator;

import java.lang.reflect.Field;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.MenuItem;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.bind.ContentPane;

/**
 * Two-frame layout. Menu on left, app-pages on right.
 */
public class SplitPaneDecorator extends AbstractDecorator<ContentPane> implements ComponentDecorator<SplitPane, FenxuiViewModel> {

	public SplitPaneDecorator(ComponentDecorator decorator) {
		super(decorator);
	}

	@Override
	public SplitPane decorate(FenxuiViewModel viewModel, FenxuiConfig fenxuiConfig) {
		GridPane leftMenu = new GridPane();
		leftMenu.setAlignment(fenxuiConfig.getAlignent());
		leftMenu.setHgap(fenxuiConfig.getHgap());
		leftMenu.setVgap(fenxuiConfig.getVgap());
		leftMenu.setPadding(fenxuiConfig.getPadding());
		AppPage appPage = viewModel.getClass().getAnnotation(AppPage.class);
		leftMenu.getStyleClass().add(appPage.cssClass());

		StackPane mainPain = new StackPane();

		SplitPane splitPane = new SplitPane(leftMenu, mainPain);
		splitPane.setDividerPosition(0, .3);
		splitPane.getStyleClass().add(appPage.cssClass());
		int i = 0;
		for (Field field : viewModel.getClass().getDeclaredFields()) {
			try {
				Object applicationPage = FieldUtils.readField(field, viewModel, true);
				ContentPane contentPane = decorator.decorate(applicationPage, fenxuiConfig);
				contentPane.setVisible(i == 0);
				mainPain.getChildren().add(contentPane);
				MenuItem annotation = field.getAnnotation(MenuItem.class);
				if (annotation != null) {
					Label label = makeLabel(annotation.value(), annotation.cssClass());
					label.setOnMouseClicked((av) -> {
						mainPain.getChildren().forEach((child) -> child.setVisible(false));
						contentPane.setVisible(true);
					});
					leftMenu.add(label, 0, i++);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return splitPane;
	}

}
