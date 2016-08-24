package br.com.mvp.instrument;

import br.com.mvp.instrument.reflection.ClassHandler;
import javassist.util.proxy.MethodHandler;

public interface Instrumentator<T> {

	public Instrumentator<T> setupProxy();
	public T newInstance(MethodHandler handler) throws Exception;
	public T newInstance() throws Exception;
	
	public ClassHandler getClassHandler();
}
