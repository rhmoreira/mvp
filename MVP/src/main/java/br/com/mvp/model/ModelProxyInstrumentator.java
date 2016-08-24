package br.com.mvp.model;

import br.com.mvp.instrument.AbstractProxyInstrumentator;
import br.com.mvp.instrument.Instrumentator;

public class ModelProxyInstrumentator<M> extends AbstractProxyInstrumentator<M>{

	public ModelProxyInstrumentator(Class<M> modelClass) throws Exception{
		super(modelClass, new ModelClassHandler(modelClass, Object.class));
	}
	
	@Override
	public Instrumentator<M> setupProxy() {
		createProxy();
		return setInterfaces(BindingModel.class).useDefaultFilter();
	}
	
	@Override
	public M newInstance() throws Exception {
		return super.newInstance(new ModelMethodHandler());
	}
}
