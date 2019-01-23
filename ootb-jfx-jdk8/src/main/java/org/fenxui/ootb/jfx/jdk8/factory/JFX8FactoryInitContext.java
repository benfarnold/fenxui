package org.fenxui.ootb.jfx.jdk8.factory;

import org.fenxui.api.factory.FieldFactory;
import org.fenxui.ootb.jfx.factory.FactoryInitContext;
import org.fenxui.ootb.jfx.jdk8.widget.factory.MonetaryFieldFactory;
import org.fenxui.ootb.jfx.jdk8.widget.factory.PercentFieldFactory;

import java.util.Map;

import static org.fenxui.api.prototype.OOTBFieldPrototypes.MONETARY_FIELD;
import static org.fenxui.api.prototype.OOTBFieldPrototypes.PERCENT_FIELD;

public class JFX8FactoryInitContext extends FactoryInitContext {

	public JFX8FactoryInitContext() {
		super();
		Map<String, FieldFactory> defaultFieldFactories = getFieldFactories();
		defaultFieldFactories.put(MONETARY_FIELD, new MonetaryFieldFactory());
		defaultFieldFactories.put(PERCENT_FIELD, new PercentFieldFactory());
	}
}
