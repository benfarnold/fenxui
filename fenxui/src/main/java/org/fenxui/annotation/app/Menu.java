package org.fenxui.annotation.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.fenxui.application.view.factory.handler.app.Orientation;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Menu {

	String cssClass() default "menupage";
	Orientation orientation() default Orientation.VERTICAL;
}
