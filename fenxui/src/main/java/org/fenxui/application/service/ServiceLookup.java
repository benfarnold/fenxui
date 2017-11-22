package org.fenxui.application.service;

import java.util.HashMap;
import java.util.Map;

public enum ServiceLookup {
	INSTANCE;
	private final Map<String, NamedService> serviceMap = new HashMap<>();

	public NamedService getService(String name) {
		return serviceMap.get(name);
	}

	public void registerService(NamedService dataService) {
		serviceMap.put(dataService.getName(), dataService);
	}

}
