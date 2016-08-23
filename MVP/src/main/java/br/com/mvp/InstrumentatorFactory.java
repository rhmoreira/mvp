package br.com.mvp;

import java.util.HashMap;
import java.util.Map;

import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.model.ModelInstrumentator;

@SuppressWarnings({"unchecked"})
final class InstrumentatorFactory {

	private enum Type{
		MODEL,
		VIEW;
	}
	
	private static Map<Class<?>, Instrumentator<?>> instrumentatorCacheMap = new HashMap<>();
	
	private InstrumentatorFactory() {}
	
	public static <T> Instrumentator<T> createModel(Class<T> clazz) throws Exception{
		return create0(Type.MODEL, clazz);
	}
	
	private static <T> Instrumentator<T> create0(Type type, Class<T> instrumentedClass) throws Exception{
		Instrumentator<?> instrumentator = instrumentatorCacheMap.get(instrumentedClass);
		if (instrumentator == null){
			switch (type) {
			case MODEL:
				instrumentator = new ModelInstrumentator<T>(Instrumentator.classPool.get(instrumentedClass.getName()));
				break;
			default:
				instrumentator = null;
			}
			instrumentatorCacheMap.put(instrumentedClass, instrumentator);
		}
		return (Instrumentator<T>) instrumentator;
	}
}