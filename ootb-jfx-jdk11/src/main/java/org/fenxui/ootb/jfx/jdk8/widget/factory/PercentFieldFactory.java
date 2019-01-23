package org.fenxui.ootb.jfx.jdk8.widget.factory;

import javafx.scene.control.Skin;

import org.fenxui.ootb.jfx.jdk8.widget.PercentageFieldSkin;
import org.fenxui.ootb.jfx.widget.SkinnableNumberField;
import org.fenxui.ootb.jfx.widget.util.PercentNumberParseStrategy;

public class PercentFieldFactory extends SkinnableFieldFactory {

	@Override
	protected SkinnableNumberField getNewInstance() {
		return new SkinnableNumberField(new PercentNumberParseStrategy()) {
			@Override
			protected Skin<?> createDefaultSkin() {
				return new PercentageFieldSkin(this);
			}
		};
	}


}
