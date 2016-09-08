package br.com.mvp.view.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.JTable;

/**
 * Model annotation for {@link JTable} components
 * @author Renato
 *
 */
@Model
@Target({FIELD})
@Retention(RUNTIME)
public @interface ViewTable {

	String fieldName() default "";
	
}
