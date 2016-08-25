package br.com.mvp.model;

import java.lang.reflect.Method;

import br.com.mvp.instrument.InstrumentatorFactory;
import br.com.mvp.instrument.MethodHandlerChain;
import br.com.mvp.instrument.ProxyInvocationHandler;
import br.com.mvp.instrument.reflection.ClassHandler;
import br.com.mvp.instrument.reflection.DependencyMapper;
import br.com.mvp.instrument.reflection.MemberHandler;

public class ModelProxyatorInvocationHandler implements ProxyInvocationHandler {

	private ClassHandler classHandler;
	
	public ModelProxyatorInvocationHandler(ClassHandler classHandler) {
		this.classHandler = classHandler;
	}
	
	@Override
	public Object invoke(Object instance, Method method, Method proceed, Object[] args, MethodHandlerChain chain) throws Throwable {
		DependencyMapper dependencyMapper = classHandler.getDependencyMapper();
		
		Class<?> modelClass = null;
		if (method.getName().startsWith("set"))
			modelClass = method.getParameterTypes()[0];
		else
			modelClass = method.getReturnType();
		
		if (dependencyMapper.isDependency(modelClass)){
			Object newInstance = InstrumentatorFactory.createModel(modelClass).newInstance();
		}
		return chain.invokeNext(instance, method, proceed, args);
	}

}
