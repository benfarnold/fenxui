package org.fenxui.application.view.factory.ootb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.FenxuiViewModel;
import org.fenxui.application.view.components.ContentPane;
import org.fenxui.application.view.components.NamedHideable;
import org.fenxui.application.view.components.menu.PageLink;
import org.fenxui.application.view.factory.PagePostProcessorPrep;
import org.fenxui.application.view.factory.handler.FieldPostProcessor;
import org.fenxui.application.view.factory.handler.PostProcessor;
import org.fenxui.application.view.factory.handler.app.Orientation;
import org.fenxui.application.view.factory.handler.page.PageContext;

/**
 * Hold context of pages and menu links for decorator to determine layout
 */
public class AppConstruction {
	private final Map<String, PagePostProcessorPrep> pagePostProcessors = new HashMap<>();
	private final Map<String, PageContext> pageContextMap = new HashMap<>();

	private final List<PostProcessor> postProcessors = new ArrayList<>();
	private final List<NamedHideable> contentPanes = new ArrayList<>();
	private final List<PageLink> menuItems = new ArrayList<>();
	private final FenxuiViewModel viewModel;
	private String menuCssClass;
	private Orientation menuOrientation;

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

	public List<NamedHideable> getContentPanes() {
		return contentPanes;
	}

	public List<PageLink> getMenuItems() {
		return menuItems;
	}

	public void addMenuItem(PageLink item) {
		menuItems.add(item);
	}

	public Orientation getMenuOrientation() {
		return menuOrientation;
	}

	public void setMenuOrientation(Orientation menuOrientation) {
		this.menuOrientation = menuOrientation;
	}

	public void addPostProcessor(PostProcessor postProcessor) {
		postProcessors.add(postProcessor);
	}

	public void postProcess() throws FenxuiInitializationException {
		for (int i = 0; i < postProcessors.size(); i++) {
			PostProcessor postProcessor = postProcessors.get(i);
			NamedHideable contentPane = contentPanes.get(i);
//			postProcessor.postProcess(contentPane);
		}
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

	public void addPageScopedFieldPostProcessor(String className, FieldPostProcessor fieldPostProcessor) {
		PagePostProcessorPrep pagePostProcessorPrep = pagePostProcessors.get(className);
		if (pagePostProcessorPrep == null) {
			pagePostProcessorPrep = new PagePostProcessorPrep();
			pagePostProcessors.put(className, pagePostProcessorPrep);
		}
		pagePostProcessorPrep.addFieldPostProcessor(fieldPostProcessor);
	}

	public void addPageContext(String name, PageContext pageContext) {
		pageContextMap.put(name, pageContext);
	}
}
