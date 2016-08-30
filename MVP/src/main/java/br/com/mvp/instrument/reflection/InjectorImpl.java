package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.InstrumentatorFactory;
import br.com.mvp.util.MVPUtil;

class InjectorImpl implements Injector {

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
		
		instanceCacheL1.put(MVPUtil.getProxiedClass(target), target);
		
		dependencyMap
			.entrySet()
			.stream()
			.forEach(entry -> {
				try{
					Object dependencyInstance = instanceCacheL1.get(entry.getValue());
					if (dependencyInstance == null){
						Instrumentator<?> instrumentator = InstrumentatorFactory.create(entry.getValue());
						dependencyInstance = instrumentator.setupProxy().newInstance();
						instanceCacheL1.put(entry.getValue(), dependencyInstance);
					}
					inject(target, dependencyInstance, entry.getKey());
				}catch (Exception e) {
					e.printStackTrace();
				}
			});
	}
}
