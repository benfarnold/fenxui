package org.fenxui.application.view.factory.ootb;

import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.factory.PagePostProcessorPrep;
import org.fenxui.application.view.factory.handler.FieldPostProcessor;
import org.fenxui.application.view.factory.handler.page.AbstractFrameContext;
import org.fenxui.application.view.factory.handler.page.PageContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Hold context of pages and menu links for decorator to determine layout
 */
public class AppConstruction extends AbstractFrameContext {
	private final Map<String, PagePostProcessorPrep> pagePostProcessors = new HashMap<>();
	private final Map<String, PageContext> pageContextMap = new HashMap<>();

	public AppConstruction() {
		super();
	}

	public void postProcess() throws FenxuiInitializationException {
		for (String pageName: pagePostProcessors.keySet()) {
			PageContext pageContext = pageContextMap.get(pageName);
			PagePostProcessorPrep fieldPostProcessor = pagePostProcessors.get(pageName);
			fieldPostProcessor.prepare(pageContext);
		}
		for (String pageName : pageContextMap.keySet()) {
			PageContext pageContext = pageContextMap.get(pageName);
			pageContext.postprocess();
		}
	}

	@Override
	public void addPageScopedFieldPostProcessor(String className, FieldPostProcessor fieldPostProcessor) {
		PagePostProcessorPrep pagePostProcessorPrep = pagePostProcessors.get(className);
		if (pagePostProcessorPrep == null) {
			pagePostProcessorPrep = new PagePostProcessorPrep();
			pagePostProcessors.put(className, pagePostProcessorPrep);
		}
		pagePostProcessorPrep.addFieldPostProcessor(fieldPostProcessor);
	}

	@Override
	public void addPageContext(String name, PageContext pageContext) {
		pageContextMap.put(name, pageContext);
	}
}
