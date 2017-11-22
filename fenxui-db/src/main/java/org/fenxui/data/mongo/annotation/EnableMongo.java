package org.fenxui.data.mongo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enable Mongo db in your application
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableMongo {

	String value();

	int port() default 27017;

	String host() default "localhost";

	String username() default "fen";

	String password() default "xui";
}
