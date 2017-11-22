package org.fenxui.application.data.bind.handler.page;

import java.lang.annotation.Annotation;
import org.fenxui.annotation.db.DataStoreObject;
import org.fenxui.application.data.DataService;
import org.fenxui.application.data.bind.BoundReferenceMap;
import org.fenxui.application.service.ServiceLookup;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;

public class DataStoreObjectAnnotationHandler implements PageAnnotationHandler {

	@Override
	public void handle(PageContext pageContext, Annotation annotation) {
		DataStoreObject dataStoreObject = (DataStoreObject) annotation;
		Class klass = dataStoreObject.value();

		DataService dataService = (DataService) ServiceLookup.INSTANCE.getService(DataService.NAME);
		dataService.registerType(klass);
		BoundReferenceMap.INSTANCE.bind(pageContext.getApplicationPage(), klass);
	}
}
