package org.fenxui.ootb.jfx.layout;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyph;
import javafx.scene.layout.Region;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.decorator.AbstractDecorator;
import org.fenxui.application.view.decorator.ComponentDecorator;
import org.fenxui.core.exception.FenxuiInitializationException;

public class JFXWindowDressingDecorator extends AbstractDecorator<Region> implements ComponentDecorator<Region> {

	public JFXWindowDressingDecorator(ComponentDecorator decorator) {
		super(decorator);
	}

	@Override
	public Region decorate(FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {
		Region mainPane = decorator.decorate(fenxuiConfig);
		JFXDecorator jfxDecor = new JFXDecorator(getStage(), mainPane);//window dressing
		jfxDecor.setCustomMaximize(true);
		jfxDecor.setGraphic(new SVGGlyph(""));
		jfxDecor.setOnCloseButtonAction(getAppFactory().getOnCloseAction());
		return jfxDecor;
	}

}
