package br.com.mvp.binding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.com.mvp.instrument.reflection.ClassHandler;
import br.com.mvp.instrument.reflection.ClassMemberFilter;

public class ViewModelFieldMatcher {

	private List<FieldMatch> match(ClassHandler viewClassHander, ClassHandler modelClassHandler){
		ClassMemberFilter modelClassFilter = modelClassHandler.getFilter();
	}
	
	public static class FieldMatch {
		
		private Field viewField;
		private Field modelField;
		private Annotation modelAnnotation;
		
		public Field getViewField() {
			return viewField;
		}
		public void setViewField(Field viewField) {
			this.viewField = viewField;
		}
		public Field getModelField() {
			return modelField;
		}
		public void setModelField(Field modelField) {
			this.modelField = modelField;
		}
		public Annotation getModelAnnotation() {
			return modelAnnotation;
		}
		public void setModelAnnotation(Annotation modelAnnotation) {
			this.modelAnnotation = modelAnnotation;
		}
	}
}
