package org.fenxui.application.view.factory.ootb;

import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.factory.FactoryInitContext;

public class DefaultAppFactory extends AbstractAppFactory implements AppFactory {
	private final Runnable onCloseAction;

	public DefaultAppFactory(Runnable onCloseAction) {
		super(new DefaultPageFactory(new FactoryInitContext()));
		this.onCloseAction = onCloseAction;
	}

	@Override
	public Runnable getOnCloseAction() {
		return onCloseAction;
	}

}
