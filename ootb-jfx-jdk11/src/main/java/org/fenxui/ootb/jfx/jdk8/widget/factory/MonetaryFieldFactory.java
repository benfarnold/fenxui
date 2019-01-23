package org.fenxui.ootb.jfx.jdk8.widget.factory;

import javafx.scene.control.Skin;
import org.fenxui.ootb.jfx.jdk8.widget.CurrencyFieldSkin;
import org.fenxui.ootb.jfx.widget.SkinnableNumberField;
import org.fenxui.ootb.jfx.widget.util.CurrencyNumberParseStrategy;

public class MonetaryFieldFactory extends SkinnableFieldFactory {

	@Override
	protected SkinnableNumberField getNewInstance() {
		return new SkinnableNumberField(new CurrencyNumberParseStrategy()) {
			@Override
			protected Skin<?> createDefaultSkin() {
				return new CurrencyFieldSkin(this);
			}
		};
	}
}
