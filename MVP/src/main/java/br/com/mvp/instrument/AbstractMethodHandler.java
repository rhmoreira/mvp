package br.com.mvp.instrument;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javassist.util.proxy.MethodHandler;

public abstract class AbstractMethodHandler implements MethodHandler {
	
	private Map<String, Boolean> handledMethodNamesMap = new HashMap<>();
	
	public AbstractMethodHandler(){
		this(new String[]{"toString", "equals", "hashCode"});
	}
	
	public AbstractMethodHandler(String... autoHandledMethods) {
		for (String methodName: autoHandledMethods)
			handledMethodNamesMap.put(methodName, Boolean.TRUE);
	}

	@Override
	public final Object invoke(Object instance, Method method, Method proceed, Object[] args) throws Throwable {
		Boolean handled = handledMethodNamesMap.get(method.getName());
		if (handled != null && handled)
			return proceed.invoke(instance, args);
		else
			return doInvoke(instance, method, proceed, args);
	}
	
	public abstract Object doInvoke(Object instance, Method method, Method proceed, Object[] args) throws Throwable;

}
