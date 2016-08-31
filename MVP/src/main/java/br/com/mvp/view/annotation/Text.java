package br.com.mvp.view.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.text.JTextComponent;

import br.com.mvp.view.converter.Converter;
import br.com.mvp.view.converter.SimpleNoConversion;

/**
 * Model annotation for any subclass of {@link JTextComponent} components
 * @author Renato
 */
@Model
@Target({FIELD})
@Retention(RUNTIME)
public @interface Text {

	String fieldName() default "";
	
	Class<? extends Converter<?,?>> converter() default SimpleNoConversion.class;
}
