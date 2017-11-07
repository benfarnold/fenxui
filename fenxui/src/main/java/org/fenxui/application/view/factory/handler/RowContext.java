package org.fenxui.application.view.factory.handler;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ArnoldBe
 */
public class RowContext {

	private final GridPane parent;
	private final int rowIndex;

	public RowContext(GridPane parent, int rowOffset) {
		this.parent = parent;
		this.rowIndex = rowOffset;
	}

	public void addToRow(Node node, int colIndex) {
		parent.add(node, colIndex, rowIndex);
	}
}
