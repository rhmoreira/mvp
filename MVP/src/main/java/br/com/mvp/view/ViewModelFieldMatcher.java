package br.com.mvp.view;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;

import br.com.mvp.reflection.ReflectionUtils;
import br.com.mvp.view.annotation.Model;

public class ViewModelFieldMatcher {

	public List<FieldMatch> match(Collection<Field> viewFields, Collection<Field> modelFields) throws Exception{
		List<FieldMatch> matches = new ArrayList<>();
		for (Field viewField: viewFields)
			for (Field modelField: modelFields)
				if (isMatch(viewField, modelField))
					matches.add(new ViewModelFieldMatcher.FieldMatch(viewField, modelField));
				
		return matches;
	}
	
	private boolean isMatch(Field vField, Field mField) throws Exception{
		String viewFieldName = vField.getName();
		String mappedName = mField.getName();
		
		Annotation a = ReflectionUtils.getStereotypedAnnotation(Model.class, mField);
		if (a != null){
			String fieldName = (String) MethodUtils.invokeMethod(a, "fieldName", new Object[]{});
			if (!fieldName.equals(""))
				mappedName = fieldName;
			if (viewFieldName.equals(mappedName))
				return true;
		}
		
		return false;
	}
	
	public static class FieldMatch {
		
		private Field viewField;
		private Field modelField;
		private Annotation modelAnnotation;
		
		public FieldMatch(Field viewField, Field modelField) {
			super();
			this.viewField = viewField;
			this.modelField = modelField;
			this.modelAnnotation = ReflectionUtils.getStereotypedAnnotation(Model.class, modelField);
		}
		
		public Field getViewField() {
			return viewField;
		}
		public Field getModelField() {
			return modelField;
		}
		public Annotation getModelAnnotation() {
			return modelAnnotation;
		}
		
		@Override
		public String toString() {
			return viewField.toString() + " | " + modelField.toString() + " | " + modelAnnotation.toString();
		}
	}
}
