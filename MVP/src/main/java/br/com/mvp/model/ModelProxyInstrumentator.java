package br.com.mvp.model;

import java.lang.reflect.Field;
import java.util.Map;

import br.com.mvp.instrument.AbstractProxyInstrumentator;
import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.reflection.ClassHandler;
import br.com.mvp.instrument.reflection.Injector;

public class ModelProxyInstrumentator<M> extends AbstractProxyInstrumentator<M>{

	private Map<Field, Class<?>> dependencyMap;
	
	public ModelProxyInstrumentator(Class<M> modelClass) throws Exception{
		super(modelClass, new ModelClassHandler(modelClass, Object.class));
		
		mapModelDependencies();
	}
	
	private void mapModelDependencies(){
		ModelDependencyMapper dependencyMapper = new ModelDependencyMapper(getClassHandler());
		dependencyMap = dependencyMapper.mapModelDependencies();
	}
	
	@Override
	public Instrumentator<M> setupProxy() {
		if (!isSetup()){
			return createProxy()
					.setInterfaces(BindingModel.class)
					.useDefaultFilter();
		}else
			return this;
	}
	
	@Override
	public M newInstance() throws Exception {
		ClassHandler classHandler = getClassHandler();
		
		ModelMethodHandler methodHandler = 
				new ModelMethodHandler( classHandler.getMappedFields(), classHandler.getMappedMethods() );
		M instance = super.newInstance(methodHandler);
		
		Injector injector = classHandler.getInjector();
		injector.inject(instance, dependencyMap);
		
		return instance;
	}
}
