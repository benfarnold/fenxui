package org.fenxui.application.view.factory.ootb;

import org.fenxui.application.view.factory.FactoryInitContext;

public class DefaultAppFactory extends AbstractAppFactory implements AppFactory {

	public DefaultAppFactory() {
		super(new DefaultPageFactory(new FactoryInitContext()));
	}

}
