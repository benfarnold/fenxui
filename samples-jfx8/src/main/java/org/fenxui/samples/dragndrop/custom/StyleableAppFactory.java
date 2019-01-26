package org.fenxui.samples.dragndrop.custom;

import javafx.scene.Node;
import org.fenxui.application.view.factory.AbstractFactoryInitContext;
import org.fenxui.application.view.factory.ootb.DefaultAppFactory;

import java.util.EnumMap;

public class StyleableAppFactory extends DefaultAppFactory {
	private EnumMap<LayoutDecorator.TargetRegion, Node> nodeLayoutMap = new EnumMap<>(LayoutDecorator.TargetRegion.class);

	public StyleableAppFactory(Runnable onCloseAction, AbstractFactoryInitContext initContext) {
		super(onCloseAction, initContext);
	}

	public Node get(LayoutDecorator.TargetRegion region) {
		return nodeLayoutMap.get(region);
	}

	public void set(LayoutDecorator.TargetRegion region, Node node) {
		nodeLayoutMap.put(region, node);
	}
}
