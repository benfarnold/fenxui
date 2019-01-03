package org.fenxui.application.view.factory;

import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.factory.handler.FieldPostProcessor;
import org.fenxui.application.view.factory.handler.page.PageContext;

import java.util.ArrayList;
import java.util.List;

public class PagePostProcessorPrep {
	private final List<FieldPostProcessor> fieldPostProcessorList = new ArrayList<>();

	public void prepare(PageContext pageContext) throws FenxuiInitializationException {
		fieldPostProcessorList.forEach(fieldPostProcessor -> {
			pageContext.addPostProcessor(fieldPostProcessor);
		});
	}

	public void addFieldPostProcessor(FieldPostProcessor fieldPostProcessor) {
		fieldPostProcessorList.add(fieldPostProcessor);
	}
}
