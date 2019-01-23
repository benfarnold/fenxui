package org.fenxui.ootb.jfx.widget.marshall;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Skin;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.ootb.jfx.jdk8.widget.CurrencyFieldSkin;
import org.fenxui.ootb.jfx.jdk8.widget.PercentageFieldSkin;
import org.fenxui.ootb.jfx.widget.SkinnableNumberField;
import org.fenxui.ootb.jfx.widget.util.CurrencyNumberParseStrategy;
import org.fenxui.ootb.jfx.widget.util.PercentNumberParseStrategy;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ObjectMarshallStrategyTest extends ApplicationTest {

	FlowPane contentPane;
	SkinnableNumberField currencyField;
	SkinnableNumberField percentField;

	@Override
	public void start(Stage stage) {
		contentPane = new FlowPane();
		contentPane.setId("content");
		currencyField = new SkinnableNumberField(new CurrencyNumberParseStrategy()) {
			@Override
			protected Skin<?> createDefaultSkin() {
				return new CurrencyFieldSkin(this);
			}
		};
		currencyField.setId("currencyField");
		percentField = new SkinnableNumberField(new PercentNumberParseStrategy()) {
			@Override
			protected Skin<?> createDefaultSkin() {
				return new PercentageFieldSkin(this);
			}
		};
		percentField.setId("percentField");
		contentPane.getChildren().addAll(currencyField, percentField);

		Scene scene = new Scene(contentPane, 800, 600);
		stage.setScene(scene);
		stage.show();
	}

	@Test
	public void marshallSkinnedNumberFieldToBigDecimal() {
		ObjectProperty bigDecimalCurrencyProperty = new SimpleObjectProperty(BigDecimal.ZERO);
		ObjectProperty bigDecimalPercentageProperty = new SimpleObjectProperty(BigDecimal.ZERO);

		FieldOption<ObjectProperty> fieldOptionCurrency = new FieldOption<>("currencyFieldName");
		fieldOptionCurrency.setValue(bigDecimalCurrencyProperty);
		FieldOption<ObjectProperty> fieldOptionPercentage = new FieldOption<>("percentageFieldName");
		fieldOptionPercentage.setValue(bigDecimalPercentageProperty);

		ObjectMarshallStrategy strategy = new ObjectMarshallStrategy();
		strategy.execute(fieldOptionCurrency, currencyField);
		strategy.execute(fieldOptionPercentage, percentField);

		clickOn("#currencyField").write("200").push(KeyCode.ENTER);
		Platform.runLater(() -> {
					assertThat(currencyField.getText(), is("$200.00"));
					assertThat(bigDecimalCurrencyProperty.get(), is(BigDecimal.valueOf(200)));
		});
		clickOn("#percentField").write("20").push(KeyCode.ENTER);
		Platform.runLater(() -> {
			assertThat(percentField.getText(), is("20%"));
			assertThat(bigDecimalPercentageProperty.get(), is(BigDecimal.valueOf(0.2)));
		});
	}
}
