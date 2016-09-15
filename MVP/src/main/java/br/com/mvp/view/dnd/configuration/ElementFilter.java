package br.com.mvp.view.dnd.configuration;

@FunctionalInterface
public interface ElementFilter<E> {

	boolean accept(E e);
}
