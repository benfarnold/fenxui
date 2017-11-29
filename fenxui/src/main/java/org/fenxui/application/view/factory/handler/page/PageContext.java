package org.fenxui.application.view.factory.handler.page;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.factory.handler.PostProcessor;

public class PageContext {

	private Object applicationPage;
	private Label title;
	private List<Node> nodes = new ArrayList<>();
	private List<PostProcessor> postProcessors = new ArrayList<>();
	private boolean autoLoad;

	public PageContext(Object applicationPage) {
		this.applicationPage = applicationPage;
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

	public void postprocess() throws FenxuiInitializationException {
		for (PostProcessor postProcessor : postProcessors) {
			for (Node node : nodes) {
				postProcessor.postProcess(node);
			}
		}
	}

	public void setAutoload(boolean autoLoad) {
		this.autoLoad = autoLoad;
	}

	public boolean isAutoLoad() {
		return autoLoad;
	}
}
