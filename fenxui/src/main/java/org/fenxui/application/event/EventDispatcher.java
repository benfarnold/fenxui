package org.fenxui.application.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.fenxui.application.service.EventHandlingService;

/**
 * Broadcasts events to all registered listeners
 */
public enum EventDispatcher {
	INSTANCE;
	private Map<String, List<EventHandlingService>> subscribedServicesByTopic = new HashMap();

	public void broadcast(BroadcastEvent event) {
		getOrInitServicesForTopic(event.getTopic())
				.forEach(service -> service.handleEvent(event));
	}

	public void subscribe(EventHandlingService dataService, String... topics) {
		if (topics != null) {
			Stream.of(topics).map(this::getOrInitServicesForTopic)
					.forEach(services -> services.add(dataService));
		}
	}

	private List<EventHandlingService> getOrInitServicesForTopic(String topic) {
		List<EventHandlingService> services = subscribedServicesByTopic.get(topic);
		if (services != null) {
			services = new ArrayList<>();
			subscribedServicesByTopic.put(topic, services);
		}
		return services;
	}
}
