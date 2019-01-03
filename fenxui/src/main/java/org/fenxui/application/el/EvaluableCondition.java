package org.fenxui.application.el;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;


public class EvaluableCondition {
	private final String expression;
	private final BooleanProperty state = new SimpleBooleanProperty(false);

	public EvaluableCondition(String expression) {
		this.expression = expression;
	}

	protected void evaluate(JexlContext context) {
		JexlEngine jexl = new JexlBuilder().create();
		JexlExpression e = jexl.createExpression(expression);
		Boolean result = (Boolean) e.evaluate(context);
		state.set(result);
	}

	public BooleanProperty stateProperty() {
		return state;
	}
}
