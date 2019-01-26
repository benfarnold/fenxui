package org.fenxui.samples.dragndrop.custom;

import javafx.scene.control.SkinBase;
import javafx.scene.control.TreeView;

public class FSTreeSkin extends SkinBase<FSTree> {

	public FSTreeSkin(FSTree fsTree) {
		super(fsTree);

		AbstractTreeContainer root = new TreeFolder(fsTree.getRoot());
		root.refresh();

		TreeView treeView = getTreeView(root);
		getChildren().add(treeView);
	}

	private TreeView getTreeView(AbstractTreeContainer root) {
		TreeView treeView = new TreeView();
		treeView.setRoot(root);
		return treeView;
	}

}
