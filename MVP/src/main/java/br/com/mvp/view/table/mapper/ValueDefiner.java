package br.com.mvp.view.table.mapper;

@FunctionalInterface
public interface ValueDefiner<T> {
	
	void define(T target, Object obj);
}
