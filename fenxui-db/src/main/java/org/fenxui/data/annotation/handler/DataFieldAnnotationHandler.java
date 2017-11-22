package org.fenxui.data.annotation.handler;

import java.lang.annotation.Annotation;
import org.apache.commons.lang3.StringUtils;
import org.fenxui.annotation.db.DataField;
import org.fenxui.application.data.bind.BoundReference;
import org.fenxui.application.data.bind.BoundReferenceMap;
import org.fenxui.application.view.factory.handler.AnnotationHandler;
import org.fenxui.application.view.factory.handler.NodeContext;

public class DataFieldAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws IllegalAccessException, NoSuchMethodException {
		DataField dataField = (DataField) annotation;
		ValueManipulatorReferenceMethods dbMethods = getMethods(dataField.value());
		ValueManipulatorReferenceMethods uiMethods = getMethods(fieldContext.getField().getName());
		BoundReference br = BoundReferenceMap.INSTANCE.get(fieldContext.getSource());
		br.putSourceValueManipulatorReference(fieldContext.getField().getName(), new ValueManipulatorReference(uiMethods, dbMethods));
	}

	private ValueManipulatorReferenceMethods getMethods(String fieldName) {
		String getMethodName = "get" + StringUtils.capitalize(fieldName);
		String setMethodName = "set" + StringUtils.capitalize(fieldName);
		return new ValueManipulatorReferenceMethods(getMethodName, setMethodName);
	}
}
