package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface ClassMemberFilter {

	boolean accept(Class<?> clazz);
	boolean accept(Field field);
	boolean accept(Method method);
}
