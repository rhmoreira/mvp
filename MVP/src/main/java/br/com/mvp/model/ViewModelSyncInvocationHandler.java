package br.com.mvp.model;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import br.com.mvp.ComponentBind;
import br.com.mvp.instrument.MethodHandlerChain;
import br.com.mvp.instrument.ProxyInvocationHandler;
import br.com.mvp.instrument.reflection.ClassHandler;

public class ViewModelSyncInvocationHandler implements ProxyInvocationHandler, BindingModel {

	private Map<String, ComponentBind> componentBindMap = new HashMap<>();
	private ClassHandler classHandler;
	
	public ViewModelSyncInvocationHandler(ClassHandler classHandler) {
		this.classHandler = classHandler;
	}

	@Override
	public Object invoke(Object instance, Method method, Method proceed, Object[] args, MethodHandlerChain chain) throws Throwable {
		return chain.invokeNext(instance, method, proceed, args);
	}
	
	@Override
	public void bindComponent(String propertyName, ComponentBind bind) {
		System.out.println("Binding component");
		componentBindMap.put(propertyName, bind);
	}
}
