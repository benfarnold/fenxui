package org.fenxui.samples.networth.ui.liabilities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.el.ExpressionFormField;

import java.math.BigDecimal;

import static org.fenxui.api.prototype.OOTBFieldPrototypes.MONETARY_FIELD;

@AppPage("Current Liabilities")
public class CurrentLiabilitiesPage {
	@FormField(label = "Credit card balances", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> creditCards = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Estimated income tax owed", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> incomeTaxOwed = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Other outstanding bills", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> other = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@ExpressionFormField(label = "Total current liabilities", factory = MONETARY_FIELD,
			expression = "#{creditCards} + #{incomeTaxOwed} + #{other}")
	private final ObjectProperty<BigDecimal> total = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);
}
