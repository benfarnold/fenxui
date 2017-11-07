package org.fenxui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to construct form fields. Value will be the label. The CSS class is
 * applied to the label
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FormField {

	String value();

	String cssClass() default "form-field";

}
