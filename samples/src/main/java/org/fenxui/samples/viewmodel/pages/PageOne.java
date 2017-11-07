package org.fenxui.samples.viewmodel.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormAction;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.FormFieldButton;
import org.fenxui.application.view.bind.widget.FenxuiTextField;
import org.fenxui.samples.viewmodel.SampleViewModel;
import org.fenxui.annotation.FitWidth;

@AppPage("Welcome")
public class PageOne {
	private SampleViewModel viewModel;

	@FormField("Name")
	private final FenxuiTextField name = new FenxuiTextField();

	@FormField("Photo")
	@FormFieldButton(value = "Browse", action = "doBrowse")
	@FitWidth(100)
	private final ImageView image = new ImageView();

	public PageOne(SampleViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public void doBrowse(ActionEvent actionEvent) throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Photo");

		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PING", "*.png"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG", "*.jpg"));
		File file = fileChooser.showOpenDialog(viewModel.getStage());
		if (file != null) {
			Image image2 = new Image(new FileInputStream(file), 100, 0, true, false);
			image.setImage(image2);
		}
		viewModel.getStage().show();
	}

	@FormAction("Save")
	public void doSave(ActionEvent actionEvent) {

	}

}
