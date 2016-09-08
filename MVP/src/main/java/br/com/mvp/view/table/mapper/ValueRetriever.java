package br.com.mvp.view.table.mapper;

@FunctionalInterface
public interface ValueRetriever<T> {
	
	Object retrieve(T target);
}
