package org.fenxui.application.data.event;

import org.fenxui.application.event.BroadcastEvent;

public class SaveEvent implements BroadcastEvent {

	public static final String TOPIC = "SaveEvent";

	private final Object subject;

	public SaveEvent(Object subject) {
		this.subject = subject;
	}

	@Override
	public String getTopic() {
		return TOPIC;
	}

	public Object getSubject() {
		return subject;
	}

}
