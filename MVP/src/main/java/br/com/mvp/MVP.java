package br.com.mvp;

import javax.swing.JPanel;

import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.view.annotation.View;

public class MVP<V extends JPanel> {
	
	public Controller<V, ?> createController(Class<V> viewClass) throws Exception{
		View view = viewClass.getAnnotation(View.class);
		
		if (view.model() == Class.class)
			throw new Exception("No models found for view " + viewClass.getName());
		
		Instrumentator<?> instrumentator = InstrumentatorFactory.createModel(view.model());
		instrumentator.instrument();
		instrumentator.getInstrumentedInstance();
		return null;
			
	}
}
