package org.fenxui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(ValueProviderValues.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValueProviderValue {
	/**
	 * The value saved
	 */
	String key();
	/**
	 * The value displayed 
	 */
	String value();
}
