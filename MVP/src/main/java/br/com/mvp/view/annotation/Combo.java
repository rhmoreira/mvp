package br.com.mvp.view.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.JComboBox;

import br.com.mvp.view.converter.Converter;
import br.com.mvp.view.converter.SimpleNoConversion;

/**
 * Model annotation for {@link JComboBox} components
 * @author Renato
 *
 */
@Model
@Target({FIELD})
@Retention(RUNTIME)
public @interface Combo {

	String fieldName() default "";

	Class<? extends Converter<?,?>> converter() default SimpleNoConversion.class;
	
}
