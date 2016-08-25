package br.com.mvp.instrument;

import java.lang.reflect.Method;

public interface ProxyInvocationHandler {

	Object invoke(Object instance, Method method, Method proceed, Object[] args, MethodHandlerChain chain) throws Throwable;
}
