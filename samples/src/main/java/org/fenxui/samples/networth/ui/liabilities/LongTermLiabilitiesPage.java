package org.fenxui.samples.networth.ui.liabilities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.el.ExpressionFormField;

import java.math.BigDecimal;

import static org.fenxui.application.view.factory.FactoryInitContext.FieldPrototype.MONETARY_FIELD;

@AppPage("Long-term liabilities")
public class LongTermLiabilitiesPage {
	@FormField(label = "Home mortgage", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> homeMortgage = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Home equity loan", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> homEquityLoan = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Mortgages on rental properties", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> mortgagesOnRentalProperties = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Car loans", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> carLoans = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Student loans", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> studentLoans = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Life insurance policy loans", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> lifeInsurancePolicyLoans = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Other long-term debt", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> other = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@ExpressionFormField(label = "Total long-term liabilities", factory = MONETARY_FIELD,
			expression = "#{homeMortgage} + #{homEquityLoan} + #{mortgagesOnRentalProperties} + #{carLoans} + #{studentLoans} + #{lifeInsurancePolicyLoans} + #{other}")
	private final ObjectProperty<BigDecimal> total = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);
}
