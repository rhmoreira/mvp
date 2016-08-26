package br.com.mvp.model;

import br.com.mvp.instrument.reflection.ClassHandler;

public class ModelClassHandler extends ClassHandler{

	public ModelClassHandler(Class<?> clazz) throws Exception {
		super(clazz, Object.class, new ModelClassFilter());
	}
}
