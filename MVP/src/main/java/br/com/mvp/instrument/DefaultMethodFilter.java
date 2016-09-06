package br.com.mvp.instrument;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodFilter;

class DefaultMethodFilter implements MethodFilter {
	
	@Override
	public boolean isHandled(Method m) {
		return !m.getName().equals("finalize") 
				|| !m.getName().equals("toString")
				|| !m.getName().equals("equals")
				|| !m.getName().equals("hashCode")
				|| !m.isBridge() 
				|| !m.isSynthetic();
	}
}
