package org.fenxui.data.annotation;

/**
 * Mark form to be autoloaded
 */
public @interface AutoLoad {

	boolean autoLoad() default true;

}
