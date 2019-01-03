package org.fenxui.application.view.components;

import com.jfoenix.controls.base.IFXValidatableControl;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Region;
import org.fenxui.application.view.bind.widget.LayoutGridPane;
import org.fenxui.application.view.bind.widget.UniqueValidatableControl;
import org.fenxui.application.view.components.option.FieldOption;

public class ContentPaneSkin extends SkinBase<ContentPane> {

	public ContentPaneSkin(ContentPane page) {
		super(page);
		LayoutGridPane gridPane = new LayoutGridPane();
		gridPane.setPadding(new Insets(20));

		List<FieldOption> options = page.getOptions();
		int i = 0;
		for (; i < options.size(); i++) {
			FieldOption option = options.get(i);
			Node node = option.getType().create(option);
			if (node instanceof IFXValidatableControl) {
				node.focusedProperty().addListener(new ChangeListener<Boolean>() {
						@Override
						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
							if (!newValue) {
								boolean isValid = ((UniqueValidatableControl) node).validate();
								page.votify(((UniqueValidatableControl) node).getUniqueId(), isValid);
							}
						}
					});
			}
			boolean expanding = FieldOption.Type.TEXT_EXPANDING == option.getType();

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
