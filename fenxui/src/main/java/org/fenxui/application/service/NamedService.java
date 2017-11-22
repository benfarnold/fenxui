package org.fenxui.application.service;

/**
 * NamedServices can be registered with the {@link ServiceLookup} so that
 * objects can register with them by name during annotation processing
 */
public interface NamedService {

	String getName();
}
