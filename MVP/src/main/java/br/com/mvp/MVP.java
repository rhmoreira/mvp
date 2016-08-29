package br.com.mvp;

import java.util.List;

import javax.swing.JPanel;

import br.com.mvp.binding.Bind;
import br.com.mvp.binding.Binding;
import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.InstrumentatorFactory;
import br.com.mvp.view.ViewModelBinder;
import br.com.mvp.view.annotation.View;

@SuppressWarnings("unchecked")
public class MVP<V extends JPanel, M> {
	
	public Controller<V, M> createController(V jpanel) throws Exception{
		Class<? extends JPanel> viewClass = jpanel.getClass();
		View view = viewClass.getAnnotation(View.class);
		
		if (view == null)
			throw new Exception("View not mapped. Use @br.com.mvp.view.annotation.View to map to a valid model-view class");
		
		Class<M> model = (Class<M>) view.model();
		if (model == Class.class)
			throw new Exception("No models found for view " + viewClass.getName() + 
					". Specify the model class using the 'model' attribute");
		
		M modelInstance = createInstance(InstrumentatorFactory.create(model));
		
		ViewModelBinder vmb = new ViewModelBinder((Bind) modelInstance, jpanel);
		List<Binding> bindingList = vmb.bind();
		((Bind) modelInstance).setBindings(bindingList);
		
		return new ControllerImpl<V, M>(jpanel, modelInstance, bindingList);
			
	}
	
	private <T> T createInstance(Instrumentator<T> instrumentator) throws Exception {
		return instrumentator
				.setupProxy()
				.newInstance();
	}
}
