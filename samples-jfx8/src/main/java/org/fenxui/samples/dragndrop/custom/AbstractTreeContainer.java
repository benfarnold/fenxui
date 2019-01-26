package org.fenxui.samples.dragndrop.custom;

import com.jfoenix.svg.SVGGlyph;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTreeContainer extends TreeItem {
	private static final Logger logger = LogManager.getLogger(AbstractTreeContainer.class);


	public AbstractTreeContainer(Object object, SVGGlyph glyph) {
		super(object, glyph);
		initChildRefresher();
		addFauxChild();
	}

	private void initChildRefresher() {
		expandedProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (newValue) {//expanded
					refresh();
				} else {
					//do nothing
				}
			} catch (Exception ex) {
				logger.error("Error refreshing directory", ex);
			}
		});
	}

	private void addFauxChild() {
		TreeItem fauxChild = new TreeItem("Please wait");//TreeItem can only be expanded when there is a child
		getChildren().add(fauxChild);
	}

	public void refresh() {
		setExpanded(true);
		Platform.runLater(() -> {
			getChildren().clear();
			addChildren();
		});
	}

	abstract protected void addChildren();
}
