package br.com.mvp.binding;
import java.lang.reflect.Method;
import java.util.Map;

import br.com.mvp.instrument.MethodHandlerChain;
import br.com.mvp.instrument.ProxyInvocationHandler;
import br.com.mvp.instrument.reflection.ClassHandler;
import br.com.mvp.instrument.reflection.ReflectionUtils;

public class BindInvocationHandler implements ProxyInvocationHandler, Bind {

	private ClassHandler classHandler;
	private Map<String, Method> bindMethods;
	
	public BindInvocationHandler(ClassHandler classHandler) throws Exception{
		this.classHandler = classHandler;
		BindClassFilter filter = new BindClassFilter();
		this.bindMethods = new ClassHandler(
									this.getClass(), 
									Object.class,
									filter,
									new BindMemberHandler(filter)
								).scan()
								 .getMappedMethods();
	}

	@Override
	public Object invoke(Object instance, Method method, Method proceed, Object[] args, MethodHandlerChain chain) throws Throwable {
		if (bindMethods.get(method.getName()) != null)
			return ReflectionUtils.invokeMethod(this, bindMethods.get(method.getName()), args);
		else
			return chain.invokeNext(instance, method, proceed, args);
	}

	@Override
	public ClassHandler getClassHandler() {
		return classHandler;
	}
}
