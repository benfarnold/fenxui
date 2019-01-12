package org.fenxui.application.view.components;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Region;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.application.view.bind.widget.LayoutGridPane;
import org.fenxui.application.view.bind.widget.UniqueValidatableControl;
import org.fenxui.application.view.components.option.FieldOption;

import java.util.List;

public class ContentPaneSkin extends SkinBase<ContentPane> {
	private static final Logger logger = LogManager.getLogger(ContentPaneSkin.class);

	public ContentPaneSkin(ContentPane page) {
		super(page);
		LayoutGridPane gridPane = new LayoutGridPane();
		gridPane.setPadding(new Insets(20));

		List<FieldOption> options = page.getOptions();
		int i = 0;
		for (; i < options.size(); i++) {
			FieldOption option = options.get(i);
			if (logger.isTraceEnabled()) {
				logger.trace("processing field: " + option.getFieldName());
			}
			Node node = option.getFieldFactory().create(option);
			if (logger.isTraceEnabled()) {
				logger.trace("field: " + option.getFieldName() + " created");
			}
			if (node instanceof UniqueValidatableControl) {
				UniqueValidatableControl control = (UniqueValidatableControl) node;
				node.focusedProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue) {
						boolean isValid = control.validate();
						page.votify(control.getUniqueId(), isValid);
					}
				});
			}
			boolean expanding = option.isBindFieldToPaneWidth();

			if (expanding) {
				Region textField = (Region) node;
				textField.minWidthProperty().bind(gridPane.minValueWidthProperty());
				textField.minWidthProperty().bind(gridPane.prefValueWidthProperty());
				textField.maxWidthProperty().bind(gridPane.maxValueWidthProperty());
			}
			if (FieldOption.LayoutSection.ADVANCED == option.getLayoutSection()) {
				gridPane.addAdditionalRow(expanding, new Label(option.getName()), node);
			} else {
				gridPane.addNormalRow(expanding, new Label(option.getName()), node);
			}
		}

		getChildren().add(gridPane);
	}

}
