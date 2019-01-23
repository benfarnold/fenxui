package org.fenxui.application.view.factory.ootb;

import org.fenxui.application.view.factory.AbstractFactoryInitContext;

public class DefaultPageFactory extends AbstractPageFactory {

	public DefaultPageFactory(AbstractFactoryInitContext factoryInitContext) {
		super(new DefaultPageContentProcessor(
				factoryInitContext.getFormFieldAnnotationHandlers(),
				factoryInitContext.getFieldFactories(),
				factoryInitContext.getValidatorFactories()
				), factoryInitContext);
	}

}
