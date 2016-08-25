package br.com.mvp.instrument;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MethodHandlerChain {
	
	private List<ProxyInvocationHandler> handlerChain = new ArrayList<>();
	private Iterator<ProxyInvocationHandler> iteratorChain;
	
	void addHandler(ProxyInvocationHandler mHandler){
		handlerChain.add(mHandler);
	}
	
	void addHandlers(ProxyInvocationHandler... handlers){
		if (handlers != null)
			for (ProxyInvocationHandler handler: handlers)
				addHandler(handler);
	}
	
	Object beginChain(Object instance, Method method, Method proceed, Object[] args) throws Throwable{
		iteratorChain = handlerChain.iterator();
		return invokeNext(instance, method, proceed, args);
	}
	
	public Object invokeNext(Object instance, Method method, Method proceed, Object[] args) throws Throwable{
		Object result = null;
		if (iteratorChain.hasNext())
			result = iteratorChain.next().invoke(instance, method, proceed, args, this);
		
		return result;
	}
}
