package br.com.mvp.instrument;

public interface InterfaceInstrumentator<T> {

	MethodInstrumentator<T> setInterfaces(Class<?>... interfaces) throws Exception;
	MethodInstrumentator<T> noExtraInterfaces();
}
