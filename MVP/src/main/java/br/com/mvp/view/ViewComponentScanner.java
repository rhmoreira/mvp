package br.com.mvp.view;

import java.lang.reflect.Field;

import javax.swing.JPanel;

import br.com.mvp.ComponentScanner;

public class ViewComponentScanner extends ComponentScanner {

	private ComponentScanner modelScanner;

	public ViewComponentScanner(ComponentScanner modelScanner, Class<?> viewClass) throws Exception {
		super(viewClass, JPanel.class);
		this.modelScanner = modelScanner;
	}

	@Override
	protected boolean accept(Field f) throws Exception {
		return false;
	}

}
