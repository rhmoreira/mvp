package br.com.mvp;

import javax.swing.JPanel;

import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.InstrumentatorFactory;
import br.com.mvp.view.annotation.View;

@SuppressWarnings("unchecked")
public class MVP<V extends JPanel> {
	
	public <M> Controller<V, M> createController(Class<V> viewClass) throws Exception{
		View view = viewClass.getAnnotation(View.class);
		
		Class<M> model = (Class<M>) view.model();
		if (model == Class.class)
			throw new Exception("No models found for view " + viewClass.getName());
		
		Instrumentator<M> instrumentator = InstrumentatorFactory.createModel(model);
		M newInstance = instrumentator
								.setupProxy()
								.newInstance();
		return new ControllerImpl<V, M>(null, newInstance);
			
	}
}
