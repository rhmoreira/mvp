package br.com.mvp.instrument;

import java.util.HashMap;
import java.util.Map;

public class InstrumentatorCache {

	private static Map<Class<?>, Instrumentator<?>> instrumentatorCacheMap = new HashMap<>();
	
	static void write(Class<?> clazz, Instrumentator<?> instrumentator){
		instrumentatorCacheMap.put(clazz, instrumentator);
	}
	
	static Instrumentator<?> read(Class<?> clazz){
		return instrumentatorCacheMap.get(clazz);
	}
	
}
