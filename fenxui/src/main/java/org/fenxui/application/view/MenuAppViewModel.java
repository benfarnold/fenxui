package org.fenxui.application.view;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.decorator.ContentPaneDecorator;
import org.fenxui.application.view.decorator.SceneDecorator;
import org.fenxui.application.view.decorator.SplitPaneDecorator;
import org.fenxui.application.view.decorator.WindowDecorator;
import org.fenxui.application.view.factory.FenxuiFactory;
import org.fenxui.application.view.factory.ootb.DefaultPageFactory;

public class MenuAppViewModel extends FenxuiViewModel {

	@Override
	public FenxuiFactory getFenxuiFactory(FenxuiConfig fenxuiConfig) {
		SceneDecorator sceneDecorator = new SceneDecorator(new WindowDecorator(new SplitPaneDecorator(new ContentPaneDecorator(new DefaultPageFactory()))));
		return fenxuiViewModel -> sceneDecorator.decorate(fenxuiViewModel, fenxuiConfig);
	}

}
