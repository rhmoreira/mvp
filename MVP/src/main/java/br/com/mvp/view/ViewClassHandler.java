package br.com.mvp.view;

import br.com.mvp.instrument.reflection.ClassHandler;

public class ViewClassHandler extends ClassHandler{

	public ViewClassHandler(Class<?> clazz, Class<?> topLevelClass) throws Exception {
		super(clazz, topLevelClass, new ViewClassFilter());
	}
}
