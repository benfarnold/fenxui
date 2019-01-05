package org.fenxui.application.view.factory.handler.page;

import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.handler.FieldPostProcessor;
import org.fenxui.application.view.factory.ootb.FrameContext;

import java.util.ArrayList;
import java.util.List;

public class PageContext extends AbstractFrameContext implements FrameContext {

	public FrameContext getFrameContext() {
		return frameContext;
	}
	private final Object applicationPage;
	private final FrameContext frameContext;

	private List<FieldOption> fieldOptions = new ArrayList<>();
	private List<FieldPostProcessor> fieldPostProcessors = new ArrayList<>();

	public PageContext(Object applicationPage, FrameContext frameContext) {
		super();
		this.applicationPage = applicationPage;
		this.frameContext = frameContext;
		frameContext.addPageContext(applicationPage.getClass().getSimpleName(), this);
	}

	public Object getApplicationPage() {
		return applicationPage;
	}

	public void addPostProcessor(FieldPostProcessor fieldPostProcessor) {
		fieldPostProcessors.add(fieldPostProcessor);
	}

	public void postprocess() throws FenxuiInitializationException {
		for (FieldPostProcessor fieldPostProcessor: fieldPostProcessors) {
			for (FieldOption fieldOption : fieldOptions) {
				fieldPostProcessor.postProcess(fieldOption);
			}
		}
	}

	public List<FieldOption> getFieldOptions() {
		return fieldOptions;
	}
	
	public void addFieldOption(FieldOption option) {
		fieldOptions.add(option);
	}

	public void addPageContext(String name, PageContext pageContext) {
		frameContext.addPageContext(name, pageContext);
	}

	@Override
	public void addPageScopedFieldPostProcessor(String className, FieldPostProcessor fieldPostProcessor) {
		frameContext.addPageScopedFieldPostProcessor(className, fieldPostProcessor);
	}

}
