package org.fenxui.ootb.jfx.factory.field;

import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXDefaultChip;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.fenxui.api.factory.FieldFactory;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.ootb.jfx.widget.FenxuiChipView;

public class ChipViewFactory implements FieldFactory<FieldOption> {

	@Override
	public Node create(FieldOption option) {
		JFXChipView<String> chipView = new FenxuiChipView();
		chipView.setStyle("-fx-background-color: WHITE;");
		chipView.setChipFactory((chip, value) -> new GuidChip(chip, value));
		//TODO: MarshallFactory
		return chipView;
	}

	private static class GuidChip extends JFXDefaultChip<String> {

		public GuidChip(JFXChipView<String> chip, String value) {
			super(chip, value);

			Label label = (Label) root.getChildren().get(0);
			label.setMaxWidth(275);
			label.setWrapText(false);
		}
	}
}
