package org.fenxui.samples.networth.ui.assets;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.fenxui.annotation.AppPage;
import org.fenxui.annotation.FormField;
import org.fenxui.annotation.el.ExpressionFormField;

import java.math.BigDecimal;

import static org.fenxui.application.view.factory.FactoryInitContext.FieldPrototype.MONETARY_FIELD;

@AppPage("Invested Assets")
public class InvestedAssetsPage {

	@FormField(label = "Taxable brokerage accounts total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> brokerageTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Other taxable accounts total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> otherTaxableTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "IRA accounts total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> iraTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Roth IRA accounts total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> rothIraTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "401(k) or 303(b) total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> taxFree401kTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "SEP-IRA", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> sepIraTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Keogh or other qualified plan", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> keoghTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Pension (vested benefit)", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> pensionTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Annuity (accumulated value)", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> annuityTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Business real estate total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> realEstateTotal = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Sole proprietorship total", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> soleProprietorship = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Partnership", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> partnership = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "C Corporation", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> cCorp = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "S Corporation", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> sCorp = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "LLC", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> llc = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@FormField(label = "Other", factory = MONETARY_FIELD)
	private final ObjectProperty<BigDecimal> other = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);

	@ExpressionFormField(label = "Total Invested Assets", factory = MONETARY_FIELD,
			expression = "#{brokerageTotal} + #{otherTaxableTotal} " +
					"+ #{iraTotal} + #{rothIraTotal} + #{taxFree401kTotal} + #{sepIraTotal}+ #{keoghTotal} + #{pensionTotal} + #{annuityTotal}" +
					"+ #{realEstateTotal} + #{soleProprietorship} + #{partnership}+ #{cCorp} + #{sCorp} + #{llc}+ #{other}")
	private final ObjectProperty<BigDecimal> total = new SimpleObjectProperty<BigDecimal>(BigDecimal.ZERO);
}
