package org.fenxui.application.view.factory.handler;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RowContext {

	private final GridPane parent;
	private final int rowIndex;

	private final int rowSpan = 1;
	private int colSpan = 1;
	private Label label;
	private Node formField;
	private Button formFieldButton;

	public RowContext(GridPane parent, int rowOffset) {
		this.parent = parent;
		this.rowIndex = rowOffset;
	}

	public int getColSpan() {
		return colSpan;
	}

	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Node getFormField() {
		return formField;
	}

	public void setFormField(Node formField) {
		this.formField = formField;
	}

	public Button getFormFieldButton() {
		return formFieldButton;
	}

	public void setFormFieldButton(Button formFieldButton) {
		this.formFieldButton = formFieldButton;
	}

	public void postProcess() {
		int colIndex = 0;
		if (colSpan == 1) {
			if (label == null) {
				label = new Label();//spacer
			}
			if (formFieldButton == null) {
				formFieldButton = new Button();  //spacer
				formFieldButton.setVisible(false);
			}
		}
		if (label != null) {
			parent.add(label, colIndex, rowIndex);
		}
		colIndex++;
		if (formField != null) {
			parent.add(formField, colIndex, rowIndex, colSpan, rowSpan);
		}
		colIndex += colSpan;
		if (formFieldButton != null) {
			parent.add(formFieldButton, colIndex, rowIndex);
		}
	}
}
