package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface ClassMemberFilter {

	boolean accept(Class<?> clazz) throws Exception;
	boolean accept(Field field) throws Exception;
	boolean accept(Method method) throws Exception;
}
