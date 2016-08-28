package br.com.mvp.instrument;

import br.com.mvp.model.ModelProxyInstrumentator;

public final class InstrumentatorFactory {

	private InstrumentatorFactory() {}
	
	public static <T> Instrumentator<T> create(Class<T> clazz) throws Exception{
		Instrumentator<T> instrumentator = InstrumentatorCache.read(clazz);
		if (instrumentator == null)
			instrumentator = new ModelProxyInstrumentator<T>(clazz);
		
		return instrumentator;
	}
	
}