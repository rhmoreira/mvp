package br.com.mvp.model;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import br.com.mvp.ComponentBind;
import br.com.mvp.instrument.AbstractMethodHandler;

public class ModelMethodHandler extends AbstractMethodHandler implements BindingModel {

	private Map<String, ComponentBind> componentBindMap = new HashMap<>();
	private Map<String, Field> fieldMap;
	private Map<String, Method> methodMap;
	
	public ModelMethodHandler(Map<String, Field> fieldMap, Map<String, Method> methodMap) {
		this.fieldMap = fieldMap;
		this.methodMap = methodMap;
	}

	@Override
	public Object doInvoke(Object instance, Method method, Method proceed, Object[] args) throws Throwable {
		System.out.println(method.getName() + " " + args);
		return proceed.invoke(instance, args);
	}
	
	@Override
	public void bindComponent(String propertyName, ComponentBind bind) {
		System.out.println("Binding component");
		componentBindMap.put(propertyName, bind);
	}
}
