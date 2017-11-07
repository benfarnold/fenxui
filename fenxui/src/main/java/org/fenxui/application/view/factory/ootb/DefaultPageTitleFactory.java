package org.fenxui.application.view.factory.ootb;

import javafx.scene.control.Label;
import org.fenxui.application.view.factory.PageTitleFactory;

public class DefaultPageTitleFactory implements PageTitleFactory {

	@Override
	public Label makeTitle(String text, String cssId) {
		Label label = new Label(text);
		label.setId(cssId + "-title");
		return label;
	}
}
