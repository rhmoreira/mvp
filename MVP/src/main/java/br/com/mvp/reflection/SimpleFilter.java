package br.com.mvp.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class SimpleFilter implements ClassMemberFilter{

	public boolean accept(Class<?> clazz){
		return true;
	}
	public boolean accept(Field field){
		return !Modifier.isStatic(field.getModifiers());
	}
	public boolean accept(Method method){
		return !Modifier.isStatic(method.getModifiers());
	}
}
