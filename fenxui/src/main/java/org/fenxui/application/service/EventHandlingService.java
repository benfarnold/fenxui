package org.fenxui.application.service;

import org.fenxui.application.event.BroadcastEvent;

public interface EventHandlingService {

	void handleEvent(BroadcastEvent sampleEvent);
}
