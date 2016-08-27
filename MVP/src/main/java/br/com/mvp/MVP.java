package br.com.mvp;

import javax.swing.JPanel;

import br.com.mvp.binding.Bind;
import br.com.mvp.binding.ViewModelBinder;
import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.InstrumentatorFactory;
import br.com.mvp.view.annotation.View;

@SuppressWarnings("unchecked")
public class MVP<V extends JPanel, M> {
	
	public Controller<V, M> createController(Class<V> viewClass) throws Exception{
		View view = viewClass.getAnnotation(View.class);
		
		Class<M> model = (Class<M>) view.model();
		if (model == Class.class)
			throw new Exception("No models found for view " + viewClass.getName());
		
		M modelInstance = createInstance(InstrumentatorFactory.createModel(model));
		V viewInstance = 	createInstance(InstrumentatorFactory.createView(viewClass));
		
		ViewModelBinder vmb = new ViewModelBinder((Bind) modelInstance, (Bind) viewInstance);
		
		return new ControllerImpl<V, M>(viewInstance, modelInstance, vmb.bind());
			
	}
	
	private <T> T createInstance(Instrumentator<T> instrumentator) throws Exception {
		return instrumentator
				.setupProxy()
				.newInstance();
	}
}
