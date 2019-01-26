package org.fenxui.samples.dragndrop.custom;

import com.jfoenix.svg.SVGGlyph;

import java.io.File;

public class TreeFile extends AbstractTreeContainer {

	public TreeFile(File file, SVGGlyph glyph) {
		super(file.getName(), glyph);

	}

	@Override
	protected void addChildren() {

	}
}
