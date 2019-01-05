package org.fenxui.application.view.factory.ootb;

import javafx.scene.control.Label;
import org.fenxui.application.view.factory.handler.FieldPostProcessor;
import org.fenxui.application.view.factory.handler.app.Orientation;
import org.fenxui.application.view.factory.handler.page.PageContext;

public interface FrameContext {
	void addPageContext(String name, PageContext pageContext);
	void addPageScopedFieldPostProcessor(String className, FieldPostProcessor fieldPostProcessor);

	void setMenuCssClass(String cssClass);

	void setMenuOrientation(Orientation orientation);

	void linkPage(String value, boolean required);

	void setTitle(Label label);

	void setPageCss(String cssClass);

	void setMenuMinWidth(int minimumWidth);

	void setMenuMinHeight(int minimumHeight);

}
