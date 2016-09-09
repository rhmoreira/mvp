package br.com.mvp.view;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import br.com.mvp.reflection.ClassMemberFilter;
import br.com.mvp.view.annotation.Model;

class ModelClassFilter implements ClassMemberFilter{

		@Override
		public boolean accept(Class<?> clazz) {
			return clazz.getAnnotation(Model.class) != null;
		}

		@Override
		public boolean accept(Field field) {
			for (Annotation a: field.getAnnotations())
				if (((Annotation)a).annotationType().isAnnotationPresent(Model.class))
					return true;
			
			return accept(field.getType());
			
		}

		@Override
		public boolean accept(Method method) {
			return method.getName().startsWith("set")
					|| method.getName().startsWith("get");
		}		
	}