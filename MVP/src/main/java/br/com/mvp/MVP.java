package br.com.mvp;

import javax.swing.JPanel;

import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.InstrumentatorFactory;
import br.com.mvp.model.BindingModel;
import br.com.mvp.view.annotation.View;

public class MVP<V extends JPanel> {
	
	public <M> Controller<V, M> createController(Class<V> viewClass) throws Exception{
		View view = viewClass.getAnnotation(View.class);
		
		if (view.model() == Class.class)
			throw new Exception("No models found for view " + viewClass.getName());
		
		Instrumentator<?> instrumentator = InstrumentatorFactory.createModel(view.model());
		Object newInstance = instrumentator
								.setupProxy()
								.newInstance();
		
		BindingModel bindingModel = (BindingModel) newInstance;
		bindingModel.bindComponent("oba", null);
		System.out.println(bindingModel);
		return null;
			
	}
}
