package org.fenxui.samples.networth.ui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.el.ExpressionFormField;

import java.math.BigDecimal;

import static org.fenxui.application.view.factory.FactoryInitContext.FieldPrototype.MONETARY_FIELD;

@AppPage("Summary")
public class Summary {
	@ExpressionFormField(label = "Total Assets", factory = MONETARY_FIELD,
			expression = "#{CashAssetsPage.total} + #{InvestedAssetsPage.total} + #{UseAssetsPage.total}")
	private final ObjectProperty<BigDecimal> totalAssets = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@ExpressionFormField(label = "Total Liabilities", factory = MONETARY_FIELD,
			expression = "#{CurrentLiabilitiesPage.total} + #{LongTermLiabilitiesPage.total}")
	private final ObjectProperty<BigDecimal> totalLiabilities = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@ExpressionFormField(label = "Net worth", factory = MONETARY_FIELD,
			expression = "#{totalAssets} - #{totalLiabilities}")
	private final ObjectProperty<BigDecimal> total = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

}
