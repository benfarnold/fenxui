package org.fenxui.application.view.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.event.Event;
import javafx.event.EventHandler;

public abstract class ReflectiveEventHandler<T extends Event> {
	protected final Method method;
	protected final Object pageModel;
	protected final EventHandler<T> eventHandler;

	public ReflectiveEventHandler(Object pageModel, Method method, EventHandler<T> eventHandler) {
		this.pageModel = pageModel;
		this.method = method;
		this.eventHandler = eventHandler;
	}

	public void handle(T event) {
		if (eventHandler != null) {//decorate event-handlers
			eventHandler.handle(event);
		}
		try {
			method.invoke(pageModel, event);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}
}
