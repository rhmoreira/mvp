package br.com.mvp.instrument;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
class InstrumentatorCache {

	private static Map<Class<?>, Instrumentator<?>> instrumentatorCacheMap = new HashMap<>();
	private static InstrumentatorCache instance = new InstrumentatorCache();
	private InstrumentatorCache(){}
	
	public static InstrumentatorCache getInstance(){
		return instance;
	}
	
	public static <T> void write(Class<T> clazz, Instrumentator<T> instrumentator){
		instrumentatorCacheMap.put(clazz, instrumentator);
	}
	
	public static <T> Instrumentator<T> read(Class<?> clazz){
		return (Instrumentator<T>) instrumentatorCacheMap.get(clazz);
	}
	
}
