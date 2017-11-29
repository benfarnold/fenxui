package org.fenxui.application.view.factory.ootb;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.bind.ContentPane;
import org.fenxui.application.view.factory.handler.PostProcessor;

/**
 * Hold context of pages and menu links for decorator to determine layout
 */
public class AppConstruction {

	private final List<PostProcessor> postProcessors = new ArrayList<>();
	private final List<ContentPane> contentPanes = new ArrayList<>();
	private final List<Node> menuItems = new ArrayList<>();
	private final FenxuiViewModel viewModel;
	private String menuCssClass;

	public AppConstruction(FenxuiViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public FenxuiViewModel getViewModel() {
		return viewModel;
	}

	public void setMenuCssClass(String cssClass) {
		this.menuCssClass = cssClass;
	}

	public String getMenuCssClass() {
		return menuCssClass;
	}

	void addContentPane(ContentPane contentPane) {
		contentPanes.add(contentPane);
	}

	public List<ContentPane> getContentPanes() {
		return contentPanes;
	}

	public List<Node> getMenuItems() {
		return menuItems;
	}

	public void addMenuItem(Node item) {
		menuItems.add(item);
	}

	public void addPostProcessor(PostProcessor postProcessor) {
		postProcessors.add(postProcessor);
	}

	public void postProcess() throws FenxuiInitializationException {
		for (int i = 0; i < postProcessors.size(); i++) {
			PostProcessor postProcessor = postProcessors.get(i);
			ContentPane contentPane = contentPanes.get(i);
			postProcessor.postProcess(contentPane);
		}
	}
}
