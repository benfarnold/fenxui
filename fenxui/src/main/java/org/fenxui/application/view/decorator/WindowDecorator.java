package org.fenxui.application.view.decorator;

import javafx.scene.layout.Region;
import org.fenxui.application.config.FenxuiConfig;
import org.fenxui.application.view.FenxuiViewModel;

/**
 * Decorates region with standard controls via the FenxuiDecorator
 */
public class WindowDecorator extends AbstractDecorator<Region> implements ComponentDecorator<FenxuiDecorator, FenxuiViewModel> {

	public WindowDecorator(ComponentDecorator regionDecorator) {
		super(regionDecorator);
	}

	@Override
	public FenxuiDecorator decorate(FenxuiViewModel fenxuiViewModel, FenxuiConfig fenxuiConfig) {
		Region region = decorator.decorate(fenxuiViewModel, fenxuiConfig);
		FenxuiDecorator fenxuiDecorator = new FenxuiDecorator(fenxuiViewModel.getStage(), region);
		fenxuiDecorator.setCustomMaximize(true);
		return fenxuiDecorator;
	}

}
