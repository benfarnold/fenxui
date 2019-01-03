package org.fenxui.application.el;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.MapContext;

import java.util.HashMap;
import java.util.Map;

public class VariableContext {
	private final EvaluableCondition condition;
	private final Map<String, StringProperty> propertyMap = new HashMap<>();
	private final IntegerProperty size = new SimpleIntegerProperty();

	public VariableContext(EvaluableCondition condition) {
		this.condition = condition;
	}

	public int getSize() {
		return size.get();
	}

	public IntegerProperty sizeProperty() {
		return size;
	}

	public void setSize(int size) {
		this.size.set(size);
	}

	public void eval() {
		condition.evaluate(createMapContext());
	}

	private JexlContext createMapContext() {
		JexlContext context = new MapContext();
		for (String key : propertyMap.keySet()) {
			context.set(key, propertyMap.get(key).get());//get current value of property
		}
		return context;
	}

	public void addVariable(String variableName, StringProperty variableInstance) {
		propertyMap.put(variableName, variableInstance);
		variableInstance.addListener((observable, oldValue, newValue) -> {
			condition.evaluate(createMapContext());
		});
		size.set(propertyMap.size());
	}
}
