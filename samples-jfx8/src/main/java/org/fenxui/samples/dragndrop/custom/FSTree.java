package org.fenxui.samples.dragndrop.custom;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.io.File;

public class FSTree extends Control {
	private final File root;

	public FSTree(String rootLocation) {
		root = new File(rootLocation);
		if (!root.exists()) {
			root.mkdirs();
		}
	}

	public File getRoot() {
		return root;
	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new FSTreeSkin(this);
	}
}
