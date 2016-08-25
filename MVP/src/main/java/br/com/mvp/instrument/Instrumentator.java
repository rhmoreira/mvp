package br.com.mvp.instrument;

import br.com.mvp.instrument.reflection.ClassHandler;

public interface Instrumentator<T> {

	public Instrumentator<T> setupProxy();
	public T newInstance(ProxyInvocationHandler... handler) throws Exception;
	public T newInstance() throws Exception;
	
	public ClassHandler getClassHandler();
}
