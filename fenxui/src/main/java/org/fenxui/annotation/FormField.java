package org.fenxui.annotation;

import com.jfoenix.validation.base.ValidatorBase;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.fenxui.application.view.components.option.FieldOption.LayoutSection;
import org.fenxui.application.view.components.option.FieldOption.Type;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FormField {
	/**
	 * Display name of field 
	 */
	String label();
	/**
	 * Type of field: controls the widget to be used to display the field
	 * ie: Text, Check box, Combo, etc
	 */
	Type type() default Type.TEXT;
	/**
	 * additional: section for additional fields (displayed when clicking 'advanced') 
	 */
	LayoutSection section() default LayoutSection.DEFAULT;
	
}
