package org.fenxui.samples.viewmodel.pages;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormAction;
import org.fenxui.application.view.bind.widget.FenxuiTextField;
import org.fenxui.samples.viewmodel.SampleViewModel;
import org.fenxui.annotation.FitWidth;
import org.fenxui.annotation.FieldButton;
import org.fenxui.annotation.FieldLabel;
import org.fenxui.application.view.bind.widget.FenxuiImageView;
import org.fenxui.application.data.event.SaveEvent;
import org.fenxui.application.event.EventDispatcher;
import org.fenxui.data.annotation.AutoLoad;
import org.fenxui.data.annotation.DataField;
import org.fenxui.data.annotation.DataTable;

@AppPage("Welcome")
@AutoLoad
@DataTable("profile")
public class PageOne {
	private SampleViewModel viewModel;

	@DataField("name")
	@FieldLabel("Name")
	private final FenxuiTextField name = new FenxuiTextField();

	@DataField("imgPath")
	@FieldLabel("Photo")
	@FieldButton(value = "Browse", action = "doBrowse")
	@FitWidth(100)
	private final FenxuiImageView image = new FenxuiImageView();

	public PageOne(SampleViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public void doBrowse(ActionEvent actionEvent) throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Photo");

		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG", "*.jpg"));
		File file = fileChooser.showOpenDialog(viewModel.getStage());
		image.setSelected(file);
		viewModel.getStage().show();
	}

	@FormAction("Save")
	public void doSave(ActionEvent actionEvent) {
		EventDispatcher.INSTANCE.broadcast(new SaveEvent(this));
	}

	//getters-setters
	public String getName() {
		return name.getText();
	}

	public void setName(String value) {
		name.setText(value);
	}

	public String getImagePath() {
		return image.getPath();
	}

	public void setImagePath(String path) {
		image.setPath(path);
	}
}
