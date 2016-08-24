package br.com.mvp.instrument;

import br.com.mvp.model.BindingModel;
import br.com.mvp.model.ModelProxyInstrumentator;
import br.com.mvp.view.ViewProxyInstrumentator;

@SuppressWarnings({"unchecked"})
public final class InstrumentatorFactory {

	private enum Type{
		MODEL,
		VIEW;
	}
	
	private InstrumentatorFactory() {}
	
	public static <T> Instrumentator<T> createModel(Class<T> clazz) throws Exception{
		return create(Type.MODEL, clazz);
	}
	
	public static <T> Instrumentator<T> createModel(Class<T> clazz, BindingModel bindingModel) throws Exception{
		return create(Type.VIEW, clazz, bindingModel);
	}
	
	private static <T> Instrumentator<T> create(Type type, Class<T> proxiedClass) throws Exception{
		return create(type, proxiedClass, null);
	}
	
	private static <T> Instrumentator<T> create(Type type, Class<T> proxiedClass, BindingModel bindingModel) throws Exception{
		Instrumentator<?> instrumentator = InstrumentatorCache.read(proxiedClass);
		if (instrumentator == null){
			switch (type) {
			case MODEL:
				instrumentator = new ModelProxyInstrumentator<T>(proxiedClass);
				break;
			case VIEW:
				instrumentator = new ViewProxyInstrumentator<T>(proxiedClass, bindingModel);
			default:
				instrumentator = null;
			}
		}
		return (Instrumentator<T>) instrumentator;
	}
	
	
}