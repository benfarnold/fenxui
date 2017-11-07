package org.fenxui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to bind properties to properties of other field. Declared on the
 * observing field
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface BindProperty {

	String bindProperty();

	String controllingFieldName();

	String controllingFieldProperty();
}
