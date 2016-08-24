package br.com.mvp.view;

import javax.swing.JPanel;

import br.com.mvp.instrument.AbstractProxyInstrumentator;
import br.com.mvp.model.BindingModel;

public class ViewProxyInstrumentator<V> extends AbstractProxyInstrumentator<V>{

	public ViewProxyInstrumentator(Class<V> viewClass, BindingModel bindingModel) throws Exception{
		super(viewClass, new ViewClassHandler(viewClass, JPanel.class));
	}
	
}
