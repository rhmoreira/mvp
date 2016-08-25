package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.util.Map;

public interface Injector {

	public void inject(Object target, Map<Field, Class<?>> dependencyMap) throws Exception;
	public void inject(Object target, Object injectee, Field field) throws Exception ;
}
