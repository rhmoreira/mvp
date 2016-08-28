package br.com.mvp.instrument.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {

	public static void setFieldValue(Object target, Object value, Field field) throws Exception{
		setAccessible(field);
		
		field.set(target, value);
	}
	
	public static <T> T getFieldValue(Object target, Field field) throws Exception{
		setAccessible(field);
		
		return (T) field.get(target);
	}
	
	private static void setAccessible(Field field){
		if (!field.isAccessible())
			field.setAccessible(true);
	}
	
	public static Object invokeMethod(Object target, Method method, Object... value) throws Exception{
		return method.invoke(target, value);
	}
	
	public static <T extends Annotation> Annotation getStereotypedAnnotation(Class<T> stereotype, Field field){
		for (Annotation a: field.getAnnotations())
			if (a.annotationType().isAnnotationPresent(stereotype))
				return a;
		
		return null;
	}
}