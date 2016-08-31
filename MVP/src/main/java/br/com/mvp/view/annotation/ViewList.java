package br.com.mvp.view.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.JList;

import br.com.mvp.view.ModelCollector;
import br.com.mvp.view.converter.ListNoConversion;

/**
 * Model annotation for {@link JList} components
 * @author Renato
 *
 */
@Model
@Target({FIELD})
@Retention(RUNTIME)
public @interface ViewList {

	String fieldName() default "";
	
	ModelCollector collectionType() default ModelCollector.SELECTED;
	
	Class<? extends ListNoConversion> converter() default ListNoConversion.class;
}
