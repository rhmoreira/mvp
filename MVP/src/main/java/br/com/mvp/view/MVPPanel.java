package br.com.mvp.view;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.mvp.Controller;
import br.com.mvp.MVP;
import br.com.mvp.binding.Binding;
import br.com.mvp.instrument.reflection.ReflectionUtils;

/**
 * Preferred SuperClass for the application View components. It contains useful methods to control the lifecycle of
 * the mapped controller and its bindings with the view.
 * @author Renato
 *
 * @param <V>
 * @param <M>
 */
public abstract class MVPPanel<V extends JPanel, M> extends JPanel{
	
	private static final long serialVersionUID = 5642290959402301950L;
	
	private Controller<V, M> controller;
	private Class<M> modelClass;
	private boolean ready;
	private ViewAction viewAction;
	
	public MVPPanel(Class<M> modelClass) {
		this.modelClass = modelClass;
	}
	
	/**
	 * Creates a wrapper for the real controller. The real controller is only injected after the panel's construction is completely finished.
	 * If not ready, any call to a controller instance method will throw an exception.
	 * @return
	 * @throws Exception
	 */
	protected Controller<V, M> getController(){
		if (controller == null)
			createController();
		return controller;
	}
	
	/**
	 * Since the model instance within the controller is a proxied object, this is a convenience method to
	 * pre-load the model instance state before rendering the view.
	 * After the copy, it calls {@link #updateView()};
	 * @param model
	 * @throws Exception
	 */
	protected void loadModel(M model) throws Exception{
		ReflectionUtils.copyProperties(model, getController().getModel());
		updateView();
	}
	
	/**
	 * Update the view components with the model attributes values.
	 * @param model
	 * @throws Exception
	 * @see 
	 * {@link #loadModel(Object)}
	 */
	protected void updateView() throws Exception{
		getController().updateView();
	}
	
	/**
	 * Update the model state with the view components values.
	 * @param model
	 * @throws Exception
	 */
	protected void updateModel() throws Exception{
		getController().updateModel();
	}
	
	/**
	 * Convinience method to be called if this panel was opened inside a JDialog container, for example.
	 * It calls the {@link Window#dispose()} method of this panel ancestor window
	 */
	public void dispose(){
		Window windowAncestor = SwingUtilities.getWindowAncestor(this);
		windowAncestor.dispose();
	}
	
	private void createController(){
		this.controller = new ControllerWrapper();
	}

	@Override
	public void addNotify() {
		super.addNotify();
		addWindowAncestorListener();
		ready = true;
		if (viewAction != null)
			viewAction.execute();
	}
	
	/**
	 * It invokes later on the {@link ViewAction#execute()} inside the overwritten {@link JComponent#addNotify()}.
	 * Since the {@link MVPPanel#getController()} returns a wrapper of a real {@link Controller} implementation,
	 * some {@link NullPointerException} can occur, it's recommended to use this method if there is any task that 
	 * could alter the model and view state during the construction of the swing components.
	 * @param delayedAction
	 * @see
	 * {@link #getController()}<br>
	 * {@link JComponent#addNotify()}
	 */
	public void invokeLater(ViewAction delayedAction){
		this.viewAction = delayedAction;
	}
	
	private void addWindowAncestorListener(){
		Window windowAncestor = SwingUtilities.getWindowAncestor(this);
		windowAncestor.addWindowListener(this.new WindowAncestorListener());
	}
	
	private class WindowAncestorListener extends WindowAdapter{
		
		@Override
		public void windowClosed(WindowEvent event) {
			try{
				MVPPanel.this.getController()
				.getBindings()
				.stream()
				.forEach(b -> b.unbind());
				
				event.getWindow().removeWindowListener(this);
				controller = null;
			}catch (Exception e) {
			}
		}
	}
	
	private class ControllerWrapper implements Controller<V, M>{
		
		private Controller<V, M> wrapped;

		@Override
		public V getView() {
			try{
				return getWrapped().getView();
			}catch (Exception e) {
				System.out.println("Component is not yet finished. View is null");				
			}
			return null;
		}

		@Override
		public M getModel() {
			try{
				return getWrapped().getModel();
			}catch (Exception e) {
				System.out.println("Component is not yet finished. Model is null");
			}
			return null;
		}

		@Override
		public void updateModel() throws Exception {
			getWrapped().updateModel();
		}

		@Override
		public void updateView() throws Exception {
			getWrapped().updateView();
		}

		@Override
		public List<Binding> getBindings() {
			return null;
		}
		
		private Controller<V, M> getWrapped() throws Exception{
			if (!MVPPanel.this.ready)
				throw new RuntimeException("Component is not completely ");
			else{
				if (this.wrapped == null){
					MVP<V, M> mvp = new MVP<>();
					this.wrapped = mvp.createController((V)MVPPanel.this, MVPPanel.this.modelClass);
				}
				return wrapped;
			}				
		}
		
	}
}
