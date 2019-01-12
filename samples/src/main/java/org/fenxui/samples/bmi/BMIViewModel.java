package org.fenxui.samples.bmi;

import javafx.beans.property.*;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.el.ExpressionFormField;
import org.fenxui.application.view.FenxuiViewModel;

import java.math.BigDecimal;

@AppPage("BMI Calculator")
public class BMIViewModel extends FenxuiViewModel {

	@FormField(label = "Feet")
	private IntegerProperty heightFeet = new SimpleIntegerProperty(0);

	@FormField(label = "Inches")
	private IntegerProperty heightInches = new SimpleIntegerProperty(1);//avoid division by 0

	@ExpressionFormField(label = "total inches", expression = "12 * #{heightFeet} + #{heightInches}")
	private IntegerProperty heightInchesTotal = new SimpleIntegerProperty();

	@FormField(label = "Weight (lbs)")
	private IntegerProperty weight = new SimpleIntegerProperty(0);

	@ExpressionFormField(label = "BMI", expression = "#{weight} / (#{heightInchesTotal} * #{heightInchesTotal} * 1.0) * 7.03", factory = "percent")//'1.0' -> avoid integer division
	private ObjectProperty<BigDecimal> bmi = new SimpleObjectProperty<>(BigDecimal.ZERO);
}
