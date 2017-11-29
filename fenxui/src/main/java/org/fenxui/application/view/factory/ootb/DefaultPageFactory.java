package org.fenxui.application.view.factory.ootb;

import org.fenxui.application.view.factory.FactoryInitContext;

public class DefaultPageFactory extends AbstractPageFactory {

	public DefaultPageFactory(FactoryInitContext factoryInitContext) {
		super(new DefaultFormGridFactory(factoryInitContext.getFormFieldAnnotationHandlers()), new DefaultFormActionFactory(), factoryInitContext);
	}

}
