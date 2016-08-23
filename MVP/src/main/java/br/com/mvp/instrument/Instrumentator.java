package br.com.mvp.instrument;

import javassist.ClassPool;

public interface Instrumentator<T> {
	
	static final ClassPool classPool = ClassPool.getDefault();

	public void instrument() throws Exception;
	public T getInstrumentedInstance() throws Exception;
}
