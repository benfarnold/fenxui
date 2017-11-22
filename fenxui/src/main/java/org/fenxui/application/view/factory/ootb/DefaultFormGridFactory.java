package org.fenxui.application.view.factory.ootb;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.factory.FormGridFactory;
import org.fenxui.application.view.factory.handler.AnnotationHandler;
import org.fenxui.application.view.factory.handler.FormContext;
import org.fenxui.application.view.factory.handler.NodeContext;
import org.fenxui.application.view.factory.handler.RowContext;

/**
 *
 * @author ArnoldBe
 */
public class DefaultFormGridFactory implements FormGridFactory {
	private final Map<Class, AnnotationHandler> annotationHandlers;

	DefaultFormGridFactory(Map<Class, AnnotationHandler> annotationHandlers) {
		this.annotationHandlers = annotationHandlers;
	}

	@Override
	public GridPane makeForm(Object applicationPage, FenxuiConfig fenxuiConfig) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		GridPane gridPane = initFormGrid(fenxuiConfig);
		int rowIndex = 0;

		FormContext formContext = new FormContext();
		for (Field field : applicationPage.getClass().getDeclaredFields()) {
			RowContext rowContext = new RowContext(gridPane, rowIndex);
			NodeContext fieldContext = new NodeContext(field, applicationPage, rowContext, formContext);
			formContext.put(field.getName(), fieldContext);
			Object fieldValue = FieldUtils.readField(field, applicationPage, true);
			if (fieldValue instanceof Node) {
				Node widget = (Node) fieldValue;
				rowContext.setFormField(widget);
				fieldContext.setNode(widget);

				for (Annotation annotation : field.getAnnotations()) {
					AnnotationHandler annotationHandler = annotationHandlers.get(annotation.annotationType());
					if (annotationHandler != null) {
						annotationHandler.handle(fieldContext, annotation);
					}
				}
				rowContext.postProcess();
				rowIndex++;
			}
		}
		formContext.postProcessNodes();
		return gridPane;
	}

	protected GridPane initFormGrid(FenxuiConfig fenxuiConfig) {
		GridPane gridPane = new GridPane();
		List<ColumnConstraints> columnConstraints = fenxuiConfig.getColConstraints();
		if (columnConstraints != null) {
			gridPane.getColumnConstraints().clear();
			columnConstraints.forEach(c -> gridPane.getColumnConstraints().add(c));
		}
		gridPane.setAlignment(Pos.CENTER_LEFT);
		gridPane.setCenterShape(true);
		gridPane.setHgap(fenxuiConfig.getHgap());
		gridPane.setVgap(fenxuiConfig.getVgap());
		gridPane.setPadding(fenxuiConfig.getPadding());
		gridPane.autosize();
		return gridPane;
	}

}
