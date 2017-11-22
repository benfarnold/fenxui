package org.fenxui.application.view.factory;

import java.util.HashMap;
import java.util.Map;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.BindProperty;
import org.fenxui.annotation.ColSpan;
import org.fenxui.annotation.FieldButton;
import org.fenxui.annotation.FieldLabel;
import org.fenxui.annotation.FitWidth;
import org.fenxui.annotation.app.Menu;
import org.fenxui.annotation.app.MenuItem;
import org.fenxui.annotation.db.AutoLoad;
import org.fenxui.annotation.db.DataStoreObject;
import org.fenxui.application.data.bind.handler.AutoLoadAnnotationHandler;
import org.fenxui.application.data.bind.handler.EnableNitriteAnnotationHandler;
import org.fenxui.application.data.bind.handler.page.DataStoreObjectAnnotationHandler;
import org.fenxui.application.view.factory.handler.AnnotationHandler;
import org.fenxui.application.view.factory.handler.BindPropertyAnnotationHandler;
import org.fenxui.application.view.factory.handler.ColSpanAnnotationHandler;
import org.fenxui.application.view.factory.handler.FieldButtonAnnotationHandler;
import org.fenxui.application.view.factory.handler.FieldLabelAnnotationHandler;
import org.fenxui.application.view.factory.handler.FitWidthAnnotationHandler;
import org.fenxui.application.view.factory.handler.app.AppAnnotationHandler;
import org.fenxui.application.view.factory.handler.app.MenuAnnotationHandler;
import org.fenxui.application.view.factory.handler.app.MenuItemAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.AppPageAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;
import org.fenxui.annotation.db.EnableMongo;

/**
 * All annotations to be handled and their handlers Anything not contained here
 * will not be processed.
 */
public class FactoryInitContext {
	private Map<Class, AnnotationHandler> formFieldAnnotationHandlers = FormFactoryPrototype.getDefaultHandlers();
	private Map<Class, AppAnnotationHandler> appAnnotationHandlers = AppFactoryPrototype.getDefaultHandlers();
	private Map<Class, PageAnnotationHandler> pageAnnotationHandlers = PageFactoryPrototype.getDefaultHandlers();

	public Map<Class, AnnotationHandler> getFormFieldAnnotationHandlers() {
		return formFieldAnnotationHandlers;
	}

	public Map<Class, AppAnnotationHandler> getAppAnnotationHandlers() {
		return appAnnotationHandlers;
	}

	public Map<Class, PageAnnotationHandler> getPageAnnotationHandlers() {
		return pageAnnotationHandlers;
	}

	public void setFormFieldAnnotationHandlers(Map<Class, AnnotationHandler> formFieldAnnotationHandlers) {
		this.formFieldAnnotationHandlers = formFieldAnnotationHandlers;
	}

	public void setAppAnnotationHandlers(Map<Class, AppAnnotationHandler> appAnnotationHandlers) {
		this.appAnnotationHandlers = appAnnotationHandlers;
	}

	public void setPageAnnotationHandlers(Map<Class, PageAnnotationHandler> pageAnnotationHandlers) {
		this.pageAnnotationHandlers = pageAnnotationHandlers;
	}

	public static class FormFactoryPrototype {
		public static Map<Class, AnnotationHandler> getDefaultHandlers() {
			Map<Class, AnnotationHandler> handlers = new HashMap<>();
			handlers.put(BindProperty.class, new BindPropertyAnnotationHandler());
			handlers.put(FieldLabel.class, new FieldLabelAnnotationHandler());
			handlers.put(FieldButton.class, new FieldButtonAnnotationHandler());
			handlers.put(FitWidth.class, new FitWidthAnnotationHandler());
			handlers.put(ColSpan.class, new ColSpanAnnotationHandler());
			return handlers;
		}
	}

	public static class AppFactoryPrototype {
		private static Map<Class, AppAnnotationHandler> getDefaultHandlers() {
			Map<Class, AppAnnotationHandler> handlers = new HashMap<>();
			handlers.put(Menu.class, new MenuAnnotationHandler());
			handlers.put(MenuItem.class, new MenuItemAnnotationHandler());
			handlers.put(EnableMongo.class, new EnableNitriteAnnotationHandler());
			return handlers;
		}
	}

	public static class PageFactoryPrototype {
		private static Map<Class, PageAnnotationHandler> getDefaultHandlers() {
			Map<Class, PageAnnotationHandler> handlers = new HashMap<>();
			handlers.put(AppPage.class, new AppPageAnnotationHandler());
			handlers.put(AutoLoad.class, new AutoLoadAnnotationHandler());
			handlers.put(DataStoreObject.class, new DataStoreObjectAnnotationHandler());
			return handlers;
		}
	}
}
