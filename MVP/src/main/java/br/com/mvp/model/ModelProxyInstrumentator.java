package br.com.mvp.model;

import br.com.mvp.binding.Bind;
import br.com.mvp.binding.BindingInvocationHandler;
import br.com.mvp.instrument.AbstractProxyInstrumentator;
import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.ProxyInvocationHandler;
import br.com.mvp.instrument.reflection.ClassHandler;
import br.com.mvp.instrument.reflection.DependencyMapper;
import br.com.mvp.instrument.reflection.Injector;

public class ModelProxyInstrumentator<M> extends AbstractProxyInstrumentator<M>{

	public ModelProxyInstrumentator(Class<M> modelClass) throws Exception{
		super(modelClass, new ModelClassHandler(modelClass));
	}
	
	@Override
	public Instrumentator<M> setupProxy() {
		if (!isSetup()){
			return createProxy()
					.setInterfaces(Bind.class)
					.useDefaultFilter();
		}else
			return this;
	}
	
	@Override
	public M newInstance() throws Exception {
		ClassHandler classHandler = getClassHandler();
		
		ProxyInvocationHandler viewModelSyncHandler = 
				new BindingInvocationHandler(classHandler);
		
		ProxyInvocationHandler modelProxyatorHandler = 
				new ModelProxyatorInvocationHandler(classHandler);
		
		M instance = super.newInstance(viewModelSyncHandler, modelProxyatorHandler);
		
		DependencyMapper dependencyMapper = classHandler.getDependencyMapper();
		
		Injector injector = classHandler.getInjector();
		injector.inject(instance, dependencyMapper.getDependencies(), classHandler.getMemberHandler());
		
		return instance;
	}
}
