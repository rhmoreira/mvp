package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {

	public static void setFieldValue(Object target, Object value, Field field) throws Exception{
		if (!field.isAccessible())
			field.setAccessible(true);
		
		field.set(target, value);
	}
	
	public static Object invokeMethod(Object target, Object value, Method method) throws Exception{
		return method.invoke(target, value);
	}
}
