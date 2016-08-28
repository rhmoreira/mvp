package br.com.mvp.binding;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import br.com.mvp.instrument.reflection.ClassMemberFilter;

public class BindClassFilter implements ClassMemberFilter {

	@Override
	public boolean accept(Class<?> clazz) {
		return true;
	}

	@Override
	public boolean accept(Field field) {
		return false;
	}

	@Override
	public boolean accept(Method method) {
		return !Modifier.isPrivate(method.getModifiers())
				&& !method.getName().equals("invoke");
	}

}
