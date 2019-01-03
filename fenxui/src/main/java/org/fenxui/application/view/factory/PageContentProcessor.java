package org.fenxui.application.view.factory;

import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.factory.handler.page.PageContext;

/**
 * Scans page class for annotated fields and adds them to PageContext
 */
public interface PageContentProcessor {

	void processPageContent(PageContext pageContext, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException;
}
