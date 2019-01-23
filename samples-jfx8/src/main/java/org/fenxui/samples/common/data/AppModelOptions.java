package org.fenxui.samples.common.data;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.function.Supplier;

public class AppModelOptions<T extends Object> {
	private final String databaseName;
	private final TypeReference<T> typeReference;
	private final Supplier<T> instantiator;

	public AppModelOptions(String databaseName, TypeReference<T> typeReference, Supplier<T> instantiator) {
		this.databaseName = databaseName;
		this.typeReference = typeReference;
		this.instantiator = instantiator;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public TypeReference<T> getTypeReference() {
		return typeReference;
	}

	public T newInstance() {
		return instantiator.get();
	}

}
