package br.com.mvp.instrument;

import br.com.mvp.model.BindingModel;
import br.com.mvp.model.ModelProxyInstrumentator;

public final class InstrumentatorFactory {

	private enum Type{
		MODEL,
		VIEW;
	}
	
	private InstrumentatorFactory() {}
	
	public static <T> Instrumentator<T> createModel(Class<T> clazz) throws Exception{
		return create(Type.MODEL, clazz);
	}
	
	public static <T> Instrumentator<T> createView(Class<T> clazz, BindingModel bindingModel) throws Exception{
		return create(Type.VIEW, clazz, bindingModel);
	}
	
	private static <T> Instrumentator<T> create(Type type, Class<T> proxiedClass) throws Exception{
		return create(type, proxiedClass, null);
	}
	
	private static <T> Instrumentator<T> create(Type type, Class<T> proxiedClass, BindingModel bindingModel) throws Exception{
		Instrumentator<T> instrumentator = InstrumentatorCache.read(proxiedClass);
		if (instrumentator == null){
			switch (type) {
			case MODEL:
				instrumentator = new ModelProxyInstrumentator<T>(proxiedClass);
				break;
			default:
				instrumentator = null;
			}
		}
		return instrumentator;
	}
	
	
}