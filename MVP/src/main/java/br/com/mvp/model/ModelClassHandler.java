package br.com.mvp.model;

import br.com.mvp.instrument.reflection.ClassHandler;

public class ModelClassHandler extends ClassHandler{

	public ModelClassHandler(Class<?> clazz, Class<?> topLevelClass) throws Exception {
		super(clazz, topLevelClass, new ModelClassFilter());
	}
}
