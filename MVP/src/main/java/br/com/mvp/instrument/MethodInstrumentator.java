package br.com.mvp.instrument;

import javassist.util.proxy.MethodFilter;

public interface MethodInstrumentator<T> {

	Instrumentator<T> setMethodFilter(MethodFilter mFilter);
	Instrumentator<T> useDefaultFilter();
	
}
