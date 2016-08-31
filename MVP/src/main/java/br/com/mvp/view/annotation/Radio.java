package br.com.mvp.view.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.com.mvp.view.converter.Converter;
import br.com.mvp.view.converter.SimpleNoConvertion;

@View
@Target({FIELD})
@Retention(RUNTIME)
public @interface Radio {

	String fieldName() default "";
	
	Class<? extends Converter<?,?>> converter() default SimpleNoConvertion.class;
}
