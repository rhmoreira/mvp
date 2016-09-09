package br.com.mvp.view;

import br.com.mvp.reflection.ClassHandler;

public class ModelClassHandler extends ClassHandler{

	public ModelClassHandler(Class<?> clazz) throws Exception {
		super(clazz, Object.class, new ModelClassFilter());
	}
}
