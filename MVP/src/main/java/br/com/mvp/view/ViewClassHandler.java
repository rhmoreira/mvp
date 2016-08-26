package br.com.mvp.view;

import javax.swing.JPanel;

import br.com.mvp.instrument.reflection.ClassHandler;

public class ViewClassHandler extends ClassHandler{

	public ViewClassHandler(Class<?> clazz) throws Exception {
		super(clazz, JPanel.class, new ViewClassFilter());
	}
}
