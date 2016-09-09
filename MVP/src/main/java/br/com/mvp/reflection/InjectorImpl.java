package br.com.mvp.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.ConstructorUtils;

class InjectorImpl implements Injector {
	
	private static final Object[] ARGS = {};

	@Override
	public void inject(Object target, Object value, Method setter) throws Exception {
		ReflectionUtils.invokeMethod(target, setter, value);
	}
	
	@Override
	public void inject(Object target, Object value, Field field) throws Exception {
		ReflectionUtils.setFieldValue(target, value, field);
	}

	@Override
	public void inject(Object target, Map<Field, Class<?>> dependencyMap, MemberHandler memberHandler) throws Exception {
		Map<Class<?>, Object> instanceCacheL1 = new HashMap<>();
		
		instanceCacheL1.put(target.getClass(), target);
		
		dependencyMap
			.entrySet()
			.stream()
			.forEach(entry -> {
				try{
					Object dependencyInstance = instanceCacheL1.get(entry.getValue());
					if (dependencyInstance == null){
						dependencyInstance = ConstructorUtils.invokeConstructor(entry.getValue(), ARGS);
						instanceCacheL1.put(entry.getValue(), dependencyInstance);
					}
					inject(target, dependencyInstance, entry.getKey());
				}catch (Exception e) {
					e.printStackTrace();
				}
			});
	}
}
