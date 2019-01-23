package org.fenxui.samples.networth.ui.assets;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.el.ExpressionFormField;

import java.math.BigDecimal;

import static org.fenxui.api.prototype.OOTBFieldPrototypes.MONETARY_FIELD;

@AppPage("Cash and cash equivalents")
public class CashAssetsPage {
	@FormField(label = "Checking accounts total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> checkingAccountsTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Savings accounts total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> savingsAccountsTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Money Market accounts total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> moneyMarketAccountsTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Savings bonds total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> savingsBondsTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "CD's total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> cdsTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Cash value of life insurance", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> cashValueOfLifeInsurance = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@ExpressionFormField(label = "Total cash", factory = MONETARY_FIELD,
			expression = "#{checkingAccountsTotal} + #{savingsAccountsTotal} + #{moneyMarketAccountsTotal} + #{savingsBondsTotal} + #{cdsTotal} + #{cashValueOfLifeInsurance}")
	private final ObjectProperty<BigDecimal> total = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);
}
