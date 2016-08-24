package br.com.mvp.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import br.com.mvp.instrument.reflection.ClassMemberFilter;
import br.com.mvp.view.annotation.Model;
import br.com.mvp.view.annotation.View;

class ModelClassFilter implements ClassMemberFilter{

		@Override
		public boolean accept(Class<?> clazz) throws Exception {
			return clazz.getAnnotation(Model.class) != null;
		}

		@Override
		public boolean accept(Field field) throws Exception {
			for (Annotation a: field.getAnnotations())
				if (((Annotation)a).annotationType().isAnnotationPresent(View.class))
					return true;
			
			return accept(field.getType());
			
		}

		@Override
		public boolean accept(Method method) throws Exception {
			return method.getName().startsWith("set")
					|| method.getName().startsWith("get");
		}		
	}