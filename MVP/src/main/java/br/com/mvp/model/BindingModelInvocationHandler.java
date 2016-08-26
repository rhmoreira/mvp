package br.com.mvp.model;
import java.lang.reflect.Method;

import br.com.mvp.ComponentBind;
import br.com.mvp.instrument.MethodHandlerChain;
import br.com.mvp.instrument.ProxyInvocationHandler;
import br.com.mvp.instrument.reflection.ClassHandler;

public class BindingModelInvocationHandler implements ProxyInvocationHandler, BindingModel {

	private ClassHandler classHandler;
	
	public BindingModelInvocationHandler(ClassHandler classHandler) {
		this.classHandler = classHandler;
	}

	@Override
	public Object invoke(Object instance, Method method, Method proceed, Object[] args, MethodHandlerChain chain) throws Throwable {
		if (method.getName().equals("bindComponent"))
			bindComponent((ComponentBind) args[0]);
		return null;
	}
	
	@Override
	public void bindComponent(ComponentBind bind) {
		System.out.println("Binding component");
	}
}
