package org.fenxui.samples.dragndrop.custom;

import javafx.scene.control.TreeItem;
import java.io.File;

public class TreeFactoryContext {
	private TreeFactoryFileStrategy treeFactoryFileStrategy;

	public void setStrategy(TreeFactoryFileStrategy strategy) {
		this.treeFactoryFileStrategy = strategy;
	}

	public TreeItem executeFileStrategy(File file) {
		return treeFactoryFileStrategy.execute(file);
	}

	public boolean acceptsFileType(File file) {
		return treeFactoryFileStrategy.acceptsFileType(file);
	}
}
