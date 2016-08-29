package br.com.mvp.view.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.DefaultListModel;

import br.com.mvp.view.ModelCollector;

@View
@Target({FIELD})
@Retention(RUNTIME)
public @interface List {

	String fieldName() default "";
	
	Class<? extends DefaultListModel<? extends Object>> model();
	
	ModelCollector collectionType() default ModelCollector.SELECTED;
}
