package br.com.mvp.view.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.JRadioButton;

import br.com.mvp.view.converter.Converter;
import br.com.mvp.view.converter.SimpleNoConversion;

/**
 * Model annotation for {@link JRadioButton} components
 * @author Renato
 *
 */
@Model
@Target({FIELD})
@Retention(RUNTIME)
public @interface Radio {

	String fieldName() default "";
	
	Class<? extends Converter<?,?>> converter() default SimpleNoConversion.class;
}
