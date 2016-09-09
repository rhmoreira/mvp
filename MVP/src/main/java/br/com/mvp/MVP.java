package br.com.mvp;

import java.util.List;

import javax.swing.JPanel;

import org.apache.commons.beanutils.ConstructorUtils;

import br.com.mvp.binding.Binding;
import br.com.mvp.view.ViewModelBinder;
import br.com.mvp.view.annotation.View;
/**
 * Convenience class to map and bind the View instance with the model
 * @author Renato
 *
 * @param <V>
 * @param <M>
 */
@SuppressWarnings("unchecked")
public class MVP<V extends JPanel, M> {
	
	/**
	 * Create a controller based on the View object. <br> 
	 * Calling this method makes the use of the attribute <code>model</code>
	 * in the @View annotation mandatory
	 * @param jpanel
	 * @return
	 * @throws Exception
	 */
	public Controller<V, M> createController(V jpanel) throws Exception{
		Class<? extends JPanel> viewClass = jpanel.getClass();
		View view = viewClass.getAnnotation(View.class);
		
		if (view == null)
			throw new Exception("View not mapped. Use @br.com.mvp.view.annotation.View to map to a valid model-view class");
		
		Class<M> model = (Class<M>) view.model();
		return createController(jpanel, model);
			
	}
	
	/**
	 * Create a controller based on the @View annotated object and a correpondent model class mapped for the view components. <br> 
	 * Calling this method DOES NOT make the use of the attribute <code>model</code> in the @View annotation mandatory
	 * @param jpanel
	 * @return
	 * @throws Exception
	 */
	public Controller<V, M> createController(V jpanel, Class<M> model) throws Exception{
		if (model == Class.class)
			throw new Exception("No models found for view " + jpanel.getClass().getName() + 
					". Specify the model class using the 'model' attribute");
		
		M modelInstance = createInstance(model);
		return createController(jpanel, modelInstance);
			
	}
	
	/**
	 * Create a controller based on the @View annotated object and a correspondent model instance mapped for the view components. <br> 
	 * Note that the model instance will be proxied and have the mapped properties copied. Which means that the instance within the Controller object 
	 * will fail on a '==' evaluation  
	 * @param jpanel
	 * @return
	 * @throws Exception
	 */
	public Controller<V, M> createController(V jpanel, M modelInstance) throws Exception{
		ViewModelBinder vmb = new ViewModelBinder(modelInstance, jpanel);
		List<Binding> bindingList = vmb.bind();
		return new ControllerImpl<V, M>(jpanel, modelInstance, bindingList);
			
	}
	
	private <T> T createInstance(Class<T> modelClass) throws Exception {
		return ConstructorUtils.invokeConstructor(modelClass, new Object[]{});
	}
}
