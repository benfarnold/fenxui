package org.fenxui.application.view.factory.handler.page;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.handler.FieldPostProcessor;
import org.fenxui.application.view.factory.handler.PostProcessor;
import org.fenxui.application.view.factory.ootb.AppConstruction;

public class PageContext {

	private final Object applicationPage;
	private final AppConstruction appConstruction;
	private String pageCss;
	private Label title;
	private List<Node> nodes = new ArrayList<>();
	private List<FieldOption> fieldOptions = new ArrayList<>();
	private List<PostProcessor> postProcessors = new ArrayList<>();
	private List<FieldPostProcessor> fieldPostProcessors = new ArrayList<>();

	public PageContext(Object applicationPage, AppConstruction appConstruction) {
		this.applicationPage = applicationPage;
		this.appConstruction = appConstruction;
		appConstruction.addPageContext(applicationPage.getClass().getSimpleName(), this);
	}

	public Object getApplicationPage() {
		return applicationPage;
	}

	public Label getTitle() {
		return title;
	}

	public void setTitle(Label title) {
		this.title = title;
	}

	public void addPostProcessor(PostProcessor postProcessor) {
		postProcessors.add(postProcessor);
	}

	public void addPostProcessor(FieldPostProcessor fieldPostProcessor) {
		fieldPostProcessors.add(fieldPostProcessor);
	}

	public void postprocess() throws FenxuiInitializationException {
		for (PostProcessor postProcessor : postProcessors) {
			for (Node node : nodes) {
				postProcessor.postProcess(node);
			}
		}
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

	public void setPageCss(String cssClass) {
		this.pageCss = cssClass;
	}

	public String getPageCss() {
		return pageCss;
	}
	
	public AppConstruction getAppConstruction() {
		return appConstruction;
	}


}
