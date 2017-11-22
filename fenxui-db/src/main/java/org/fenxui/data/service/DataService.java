package org.fenxui.data.service;

import java.util.List;
import java.util.Map;
import org.fenxui.application.service.EventHandlingService;
import org.fenxui.application.service.NamedService;

/**
 * central controller of binding view-data object binding
 */
public interface DataService<R, U, Q> extends EventHandlingService, NamedService {

	public static String NAME = "DataService";

	@Override
	default public String getName() {
		return NAME;
	}

	void register(R registerable);

}
