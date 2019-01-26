package org.fenxui.samples.dragndrop.custom;

import javafx.scene.control.TreeItem;

import java.io.File;

public interface TreeFactoryFileStrategy {

	TreeItem execute(File file);

	boolean acceptsFileType(File file);
}
