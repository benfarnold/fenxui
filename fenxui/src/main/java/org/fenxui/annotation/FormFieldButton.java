package org.fenxui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A button to decorate the associated field. ie: Browse
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FormFieldButton {

	String value();

	String cssClass() default "form-field";

	String action() default "";
}
