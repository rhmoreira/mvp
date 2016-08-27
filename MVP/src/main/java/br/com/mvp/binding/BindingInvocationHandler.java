package br.com.mvp.binding;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.mvp.instrument.MethodHandlerChain;
import br.com.mvp.instrument.ProxyInvocationHandler;
import br.com.mvp.instrument.reflection.ClassHandler;

@SuppressWarnings("unchecked")
public class BindingInvocationHandler implements ProxyInvocationHandler, Bind {

	private ClassHandler classHandler;
	private Object instance;
	
	private static Set<String> bindMethodsSet = new HashSet<>(
			Arrays.asList("getClassHandler", "getInstance", "setBindings", "undoBindings", "sync"));
	
	public BindingInvocationHandler(ClassHandler classHandler) {
		this.classHandler = classHandler;
	}

	@Override
	public Object invoke(Object instance, Method method, Method proceed, Object[] args, MethodHandlerChain chain) throws Throwable {
		this.instance = instance;
		
		switch (method.getName()) {
		case "getClassHandler":
			return getClassHandler();
		case "getInstance":
			return getInstance();
		case "setBindings":
			setBindings((List<Binding>) args[0]);
			return null;
		case "undoBindings":
			undoBindings();
			return null;
		case "sync":
			sync();
			return null;
		default:
			return chain.invokeNext(instance, method, proceed, args);
		}
	}

	@Override
	public ClassHandler getClassHandler() {
		return classHandler;
	}

	@Override
	public Object getInstance() {
		return instance;
	}
	
	@Override
	public void setBindings(List<Binding> binding) {
		
	}
	
	@Override
	public void sync() {
		
	}

	@Override
	public void undoBindings() {
		
	}
}
