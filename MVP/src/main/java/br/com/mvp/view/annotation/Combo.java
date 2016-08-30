package br.com.mvp.view.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@View
@Target({FIELD})
@Retention(RUNTIME)
public @interface Combo {

	String fieldName() default "";
}
