package org.fenxui.samples.networth.ui.assets;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.el.ExpressionFormField;

import java.math.BigDecimal;

import static org.fenxui.application.view.factory.FactoryInitContext.FieldPrototype.MONETARY_FIELD;

@AppPage("Use Assets")
public class UseAssetsPage {
	@FormField(label = "Principal home", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> principalHome = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Vacation home", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> vacationHome = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Cars, trucks, boats", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> vehicles = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Home furnishings", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> homeFurnishings = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Art, antiques, collectibles", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> collectibles = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Jewelry, furs", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> jewelry = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Other", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> other = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@ExpressionFormField(label = "Total Use Assets", factory = MONETARY_FIELD,
			expression = "#{principalHome} + #{vacationHome} + #{vehicles} + #{homeFurnishings} + #{collectibles} + #{jewelry} + #{other}")
	private final ObjectProperty<BigDecimal> total = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);
}
