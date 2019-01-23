package org.fenxui.ootb.jfx.widget;

import com.jfoenix.controls.JFXChipView;
import org.fenxui.application.view.components.HeightSizedNode;

public class FenxuiChipView extends JFXChipView<String> implements HeightSizedNode {

	@Override
	public int getMultiplier() {
		return 3;
	}
}
