package org.fenxui.data.bind;

import java.util.HashMap;
import java.util.Map;

/**
 * Used to look up the Id of a bound page
 */
public enum BoundReferenceMap {
	INSTANCE;
	private Map<Object, BoundReference> map = new HashMap<>();

	public void bind(Object page, String table) {
		map.put(page, new BoundReference(new IdBindable(), page, dbClass));
	}

	public BoundReference get(Object page) {
		return map.get(page);
	}
}
