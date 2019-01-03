package org.fenxui.application.view.decorator;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyph;
import javafx.scene.layout.Region;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.FenxuiViewModel;

public class JFXWindowDressingDecorator extends AbstractDecorator<Region> implements ComponentDecorator<Region, FenxuiViewModel> {

	public JFXWindowDressingDecorator(ComponentDecorator decorator) {
		super(decorator);
	}

	@Override
	public Region decorate(FenxuiViewModel fenxuiViewModel, FenxuiConfig fenxuiConfig) throws FenxuiInitializationException {
		Region mainPane = decorator.decorate(fenxuiViewModel, fenxuiConfig);
		JFXDecorator jfxDecor = new JFXDecorator(fenxuiViewModel.getStage(), mainPane);//window dressing
		jfxDecor.setCustomMaximize(true);
		jfxDecor.setGraphic(new SVGGlyph(""));
		jfxDecor.setOnCloseButtonAction(getAppFactory().getOnCloseAction());
		return jfxDecor;
	}

}
