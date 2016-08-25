package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.InstrumentatorFactory;

class InjectorImpl implements Injector {

	
	@Override
	public void inject(Object target, Object value, Field field) throws Exception {
		if (!field.isAccessible())
			field.setAccessible(true);
		
		field.set(target, value);
	}

	@Override
	public void injectInstrumented(Object target, Class<?> targetOriginalClass, Map<Field, Class<?>> dependencyMap) throws Exception {
		Map<Class<?>, Object> instanceCacheL1 = new HashMap<>();
		instanceCacheL1.put(targetOriginalClass, target);
		dependencyMap
			.entrySet()
			.stream()
			.forEach(entry -> {
				try{
					Object dependencyInstance = instanceCacheL1.get(entry.getValue());
					if (dependencyInstance == null){
						Instrumentator<?> instrumentator = InstrumentatorFactory.createModel(entry.getValue());
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
