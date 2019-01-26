package org.fenxui.samples.dragndrop.custom;

import com.jfoenix.svg.SVGGlyph;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.application.view.components.option.ColorOptions;
import org.fenxui.core.exception.FenxuiInitializationException;
import org.fenxui.ootb.jfx.widget.util.GlyphUtil;

import java.io.File;
import java.util.stream.Stream;

public class TreeFolder extends AbstractTreeContainer {
	private static final Logger logger = LogManager.getLogger(TreeFolder.class);

	private final TreeFactoryContext treeFactoryContext;
	private final File folder;
	private final StackPane labelStack;
	private final StringProperty name = new SimpleStringProperty();
	private final Label nameLabel;
	private final TextField editLabel;

	private final ContextMenu contextMenu = new ContextMenu();
	private int nextSuffix = 0;

	private static SVGGlyph glyph;

	static {
		try {
			glyph = GlyphUtil.getGlyph("icomoon.svg.folder", ColorOptions.YELLOWISH);
		} catch (FenxuiInitializationException e) {
			logger.error("Error loading folder icon", e);
		}
	}

	public TreeFolder(File folder, TreeFactoryContext treeFactoryContext) {
		super(new StackPane(), glyph);
		this.treeFactoryContext = treeFactoryContext;
		this.folder = folder;
		labelStack = (StackPane) getValue();

		name.set(folder.getName());
		name.addListener((observable, oldValue, newValue) -> {
			File destination = new File(folder.getParent() + File.separator + newValue);
			folder.renameTo(destination);
		});
		nameLabel = new Label();
		editLabel = new TextField();
		nameLabel.textProperty().bind(name);
		//only rename folder when they hit enter
		editLabel.setOnAction(event -> {
			name.set(editLabel.getText());
			nameLabel.setVisible(true);
			editLabel.setVisible(false);
		});
		nameLabel.setVisible(true);
		editLabel.setVisible(false);
		labelStack.getChildren().addAll(nameLabel, editLabel);

		initShortCutMenu();

	}

	private void initShortCutMenu() {
		super.getGraphic().setOnContextMenuRequested(event -> contextMenu.show(getGraphic(), event.getScreenX(), event.getScreenY()));
		MenuItem addFolder = new MenuItem("Add folder");
		addFolder.setOnAction(event -> {
			String newFilename = nextSuffix < 1 ? "new folder" : "new folder (" + nextSuffix + ")";
			File newFolder = new File(folder.getAbsolutePath() + File.separator + newFilename);
			newFolder.mkdirs();
			refresh();
			nextSuffix++;
		});
		MenuItem renameFolder = new MenuItem("Rename");
		renameFolder.setOnAction(event -> {
			nameLabel.setVisible(false);
			editLabel.setVisible(true);
		});
		contextMenu.getItems().addAll(addFolder, renameFolder);
	}


	@Override
	protected void addChildren() {
		Stream.of(folder.listFiles())
				.filter(f->f.isDirectory())
				.forEach(f-> {
					TreeFolder treeFolder = new TreeFolder(f, treeFactoryContext);
					getChildren().add(treeFolder);
				});
		Stream.of(folder.listFiles())
				.filter(f->!f.isDirectory())
				.filter(treeFactoryContext::acceptsFileType)
				.forEach(treeFactoryContext::executeFileStrategy);
	}
}
