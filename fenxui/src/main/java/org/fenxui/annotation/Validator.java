package org.fenxui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.fenxui.application.view.components.option.ValidatorOptions;

@Repeatable(Validators.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validator {
	ValidatorOptions type();
	String message();
	/**
	 * Expression to evaluate.
	 * When true, validation is applied.
	 * Encode variables as ${fieldName} or ${className.fieldName}
	 */
	String evalExpression() default "";
}
