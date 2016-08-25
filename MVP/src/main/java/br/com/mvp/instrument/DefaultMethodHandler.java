package br.com.mvp.instrument;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javassist.util.proxy.MethodHandler;

class DefaultMethodHandler implements MethodHandler, ProxyInvocationHandler{
	
	private Map<String, Boolean> handledMethodNamesMap = new HashMap<>();
	private MethodHandlerChain chain = new MethodHandlerChain();
	
	public DefaultMethodHandler(ProxyInvocationHandler... handlers){
		this(new String[]{"toString", "equals", "hashCode"});
		
		chain.addHandlers(handlers);
		chain.addHandler(this);
	}
	
	public DefaultMethodHandler(String... autoHandledMethods) {
		for (String methodName: autoHandledMethods)
			handledMethodNamesMap.put(methodName, Boolean.TRUE);
	}

	@Override
	public final Object invoke(Object instance, Method method, Method proceed, Object[] args) throws Throwable {
		Boolean handled = handledMethodNamesMap.get(method.getName());
		if (handled != null && handled )
			return proceed.invoke(instance, args);
		else
			return chain.beginChain(instance, method, proceed, args);
	}

	@Override
	public Object invoke(Object instance, Method method, Method proceed, Object[] args, MethodHandlerChain chain)
			throws Throwable {
		return proceed.invoke(instance, args);
	}
}