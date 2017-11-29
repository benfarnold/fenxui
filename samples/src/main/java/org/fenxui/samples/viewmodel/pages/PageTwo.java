package org.fenxui.samples.viewmodel.pages;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.BindProperty;
import org.fenxui.annotation.ColSpan;
import org.fenxui.annotation.FieldLabel;
import org.fenxui.application.view.bind.widget.FenxuiCheckBox;
import org.fenxui.samples.viewmodel.SampleViewModel;

@AppPage("Settings")
public class PageTwo {
	private SampleViewModel viewModel;

	@FieldLabel("I like trains")
	private final FenxuiCheckBox checkBox = new FenxuiCheckBox();

	@ColSpan(3)
	@BindProperty(bindProperty = "visible", controllingFieldName = "checkBox", controllingFieldProperty = "selected")
	private ImageView imageView = new ImageView();

	public PageTwo(SampleViewModel viewModel) {
		this.viewModel = viewModel;
		Image image = new Image("train.jpg");
		imageView.setImage(image);
	}


}
