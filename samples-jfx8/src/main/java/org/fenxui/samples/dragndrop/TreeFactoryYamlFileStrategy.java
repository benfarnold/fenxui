package org.fenxui.samples.dragndrop;

import com.jfoenix.svg.SVGGlyph;
import javafx.scene.control.TreeItem;
import org.fenxui.application.view.components.option.ColorOptions;
import org.fenxui.core.exception.FenxuiInitializationException;
import org.fenxui.ootb.jfx.widget.util.GlyphUtil;
import org.fenxui.samples.dragndrop.custom.TreeFactoryFileStrategy;
import org.fenxui.samples.dragndrop.custom.TreeFile;

import java.io.File;

public class TreeFactoryYamlFileStrategy implements TreeFactoryFileStrategy {
	private final SVGGlyph glyph;

	public TreeFactoryYamlFileStrategy() throws FenxuiInitializationException {
		glyph = GlyphUtil.getGlyph("icomoon.svg.folder", ColorOptions.YELLOWISH);
	}

	@Override
	public TreeItem execute(File file) {
		TreeFile treeFile = new TreeFile(file, glyph);
		treeFile.refresh();
		return treeFile;
	}

	@Override
	public boolean acceptsFileType(File file) {
		return file.getName().endsWith(".yaml");
	}
}
