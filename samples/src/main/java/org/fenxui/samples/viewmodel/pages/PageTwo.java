package org.fenxui.samples.viewmodel.pages;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.BindProperty;
import org.fenxui.annotation.FormField;
import org.fenxui.application.view.bind.widget.FenxuiCheckBox;
import org.fenxui.samples.viewmodel.SampleViewModel;

@AppPage("Settings")
public class PageTwo {
	private SampleViewModel viewModel;

	@FormField("I like trains")
	private final FenxuiCheckBox checkBox = new FenxuiCheckBox();

	@FormField("")
	@BindProperty(bindProperty = "visible", controllingFieldName = "checkBox", controllingFieldProperty = "selected")
	private ImageView imageView = new ImageView();

	public PageTwo(SampleViewModel viewModel) {
		this.viewModel = viewModel;
		Image image = new Image("train.jpg");
		imageView.setImage(image);
	}


}
