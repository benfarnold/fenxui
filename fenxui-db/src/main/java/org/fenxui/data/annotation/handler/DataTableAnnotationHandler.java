package org.fenxui.data.annotation.handler;

import java.lang.annotation.Annotation;
import org.fenxui.application.service.ServiceLookup;
import org.fenxui.application.view.factory.handler.page.PageAnnotationHandler;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.data.annotation.DataTable;
import org.fenxui.data.bind.BoundReferenceMap;
import org.fenxui.data.service.DataService;

public class DataTableAnnotationHandler implements PageAnnotationHandler {

	@Override
	public void handle(PageContext pageContext, Annotation annotation) {
		DataTable dataTable = (DataTable) annotation;
		String tableName = dataTable.value();

		DataService dataService = (DataService) ServiceLookup.INSTANCE.getService(DataService.NAME);
		dataService.registerTable(tableName);
		BoundReferenceMap.INSTANCE.bind(pageContext.getApplicationPage(), tableName);
	}
}
