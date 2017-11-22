package org.fenxui.application.view.bind.widget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.fenxui.application.data.bind.DataBindable;

/**
 * ImageView with the file path included Automatically loads image into
 * imageView when provided a path
 */
public class FenxuiImageView extends ImageView implements DataBindable<String> {

	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		loadImage();
	}

	private void loadImage() {
		if (path != null) {
			File file = new File(path);
			loadImage(file);
		}
	}

	public void loadImage(File file) {
		if (file != null) {
			try {
				Image image2 = new Image(new FileInputStream(file), 100, 0, true, false);
				setImage(image2);
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void setSelected(File file) {
		if (file != null) {
			loadImage(file);
			path = file.getPath();
		}
	}

	@Override
	public String getValue() {
		return getPath();
	}

	@Override
	public void setValue(String value) {
		setPath(value);
	}

}
