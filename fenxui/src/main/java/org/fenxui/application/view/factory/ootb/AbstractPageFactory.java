package org.fenxui.application.view.factory.ootb;

import java.lang.reflect.InvocationTargetException;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.fenxui.annotation.AppPage;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.bind.ContentPane;
import org.fenxui.application.view.factory.FormActionFactory;
import org.fenxui.application.view.factory.FormGridFactory;
import org.fenxui.application.view.factory.PageTitleFactory;

/**
 * Lays out page as a grid within a vbox vbox: title, grid grid: Row 1....n form
 * elements 1..n (A form element is typically a label and a field, but may also
 * include a button that populates the field) Row n+1 form action buttons row
 */
public class AbstractPageFactory {
	private final PageTitleFactory titleFactory;
	private final FormGridFactory formGridFactory;
	private final FormActionFactory formActionFactory;

	public AbstractPageFactory(PageTitleFactory titleFactory, FormGridFactory formRowFactory, FormActionFactory formActionFactory) {
		this.titleFactory = titleFactory;
		this.formGridFactory = formRowFactory;
		this.formActionFactory = formActionFactory;
	}

	public ContentPane makePage(Object applicationPage, FenxuiConfig fenxuiConfig) {
		ContentPane contentPane = new ContentPane();
		contentPane.setAlignment(fenxuiConfig.getAlignent());
//		contentPane.setPadding(fenxuiConfig.getPadding());
		contentPane.autosize();
		int rowIndex = 0;

		try {
			AppPage appPage = applicationPage.getClass().getAnnotation(AppPage.class);
			contentPane.getChildren().add(titleFactory.makeTitle(appPage.value(), appPage.cssClass()));
			GridPane gridPane = formGridFactory.makeForm(applicationPage);
			gridPane.setAlignment(fenxuiConfig.getAlignent());
			gridPane.setHgap(fenxuiConfig.getHgap());
			gridPane.setVgap(fenxuiConfig.getVgap());
			gridPane.setPadding(fenxuiConfig.getPadding());
//			gridPane.autosize();
			contentPane.getChildren().add(gridPane);
			HBox hbBtn = formActionFactory.makeFormActionRow(applicationPage);
			contentPane.getChildren().add(hbBtn);
//			gridPane.add(hbBtn, 1, rowIndex++);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return contentPane;
	}
}
