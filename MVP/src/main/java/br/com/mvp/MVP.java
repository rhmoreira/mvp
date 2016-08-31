package br.com.mvp;

import java.util.List;

import javax.swing.JPanel;

import br.com.mvp.binding.Bind;
import br.com.mvp.binding.Binding;
import br.com.mvp.instrument.InstrumentatorFactory;
import br.com.mvp.instrument.reflection.ReflectionUtils;
import br.com.mvp.util.MVPUtil;
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
		return createController(jpanel, model);
			
	}
	
	public Controller<V, M> createController(V jpanel, Class<M> model) throws Exception{
		if (model == Class.class)
			throw new Exception("No models found for view " + jpanel.getClass().getName() + 
					". Specify the model class using the 'model' attribute");
		
		M modelInstance = createInstance(model);
		return createController(jpanel, modelInstance);
			
	}
	
	public Controller<V, M> createController(V jpanel, M modelInstance) throws Exception{
		if (!MVPUtil.isProxiedClass(modelInstance.getClass())){
			M instance = createInstance( (Class<M>) modelInstance.getClass());
			ReflectionUtils.copyProperties(modelInstance, instance);
			modelInstance = instance;
		}
		
		ViewModelBinder vmb = new ViewModelBinder((Bind) modelInstance, jpanel);
		List<Binding> bindingList = vmb.bind();
		return new ControllerImpl<V, M>(jpanel, modelInstance, bindingList);
			
	}
	
	private <T> T createInstance(Class<T> modelClass) throws Exception {
		return InstrumentatorFactory.create(modelClass)
				.setupProxy()
				.newInstance();
	}
}
