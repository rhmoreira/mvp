package br.com.mvp;

import javax.swing.JPanel;

import br.com.mvp.binding.Bind;
import br.com.mvp.binding.ViewModelBinder;
import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.InstrumentatorFactory;
import br.com.mvp.view.annotation.View;

@SuppressWarnings("unchecked")
public class MVP<V extends JPanel, M> {
	
	public Controller<V, M> createController(V jpanel) throws Exception{
		Class<? extends JPanel> viewClass = jpanel.getClass();
		View view = viewClass.getAnnotation(View.class);
		
		Class<M> model = (Class<M>) view.model();
		if (model == Class.class)
			throw new Exception("No models found for view " + viewClass.getName());
		
		M modelInstance = createInstance(InstrumentatorFactory.create(model));
		
		ViewModelBinder vmb = new ViewModelBinder((Bind) modelInstance, jpanel);
		
		return new ControllerImpl<V, M>(jpanel, modelInstance, vmb.bind());
			
	}
	
	private <T> T createInstance(Instrumentator<T> instrumentator) throws Exception {
		return instrumentator
				.setupProxy()
				.newInstance();
	}
}
