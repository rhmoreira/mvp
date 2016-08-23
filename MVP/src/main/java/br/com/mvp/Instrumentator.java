package br.com.mvp;

public interface Instrumentator<T> {

	public T get() throws Exception;
	public ComponentScanner getScanner();
}
