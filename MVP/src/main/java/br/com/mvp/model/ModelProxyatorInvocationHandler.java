package br.com.mvp.model;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;

import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.InstrumentatorFactory;
import br.com.mvp.instrument.MethodHandlerChain;
import br.com.mvp.instrument.ProxyInvocationHandler;
import br.com.mvp.instrument.reflection.ClassHandler;
import br.com.mvp.instrument.reflection.DependencyMapper;
import br.com.mvp.util.MVPUtil;

public class ModelProxyatorInvocationHandler implements ProxyInvocationHandler {

	private ClassHandler classHandler;
	
	public ModelProxyatorInvocationHandler(ClassHandler classHandler) {
		this.classHandler = classHandler;
	}
	
	@Override
	public Object invoke(Object instance, Method method, Method proceed, Object[] args, MethodHandlerChain chain) throws Throwable {
		
		Class<?> parameterType = getParameterType(method);
		
		DependencyMapper dependencyMapper = classHandler.getDependencyMapper();
		if (dependencyMapper.isDependency(parameterType)){
			if (args[0] != null){
				if (!MVPUtil.isProxiedClass(args[0].getClass())){
					Object proxiedModel = createModelReplacement(args[0]);
					BeanUtils.copyProperties(proxiedModel, args[0]);
					args[0] = proxiedModel;
				}
			}
		}
		return chain.invokeNext(instance, method, proceed, args);
	}
	
	private Object createModelReplacement(Object model) throws Exception {
		Instrumentator<Object> instrumentator = InstrumentatorFactory.create(MVPUtil.getProxiedClass(model));
		return instrumentator.newInstance();
	}

	private Class<?> getParameterType(Method method){
		Class<?> modelClass = null;
		if (method.getName().startsWith("set")){
			modelClass = method.getParameterTypes()[0];
		}
		return modelClass;
	}

}
