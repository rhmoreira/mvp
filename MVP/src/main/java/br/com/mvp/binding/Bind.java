package br.com.mvp.binding;

import java.util.List;

import br.com.mvp.instrument.reflection.ClassHandler;

public interface Bind {

	ClassHandler getClassHandler();
	Object getInstance();
	
	void setBindings(List<Binding> bindings);
	void replace(Binding binding);
	void undoBindings();
	void sync();
}
