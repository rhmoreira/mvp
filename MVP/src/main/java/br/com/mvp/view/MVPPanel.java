package br.com.mvp.view;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.mvp.Controller;
import br.com.mvp.MVP;
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
	
	public MVPPanel(Class<M> modelClass) {
		this.modelClass = modelClass;
	}
	
	/**
	 * Creates the controller if its <code>null</code>
	 * @return
	 * @throws Exception
	 */
	protected Controller<V, M> getController() throws Exception{
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
	
	private void createController() throws Exception{
		MVP<V, M> mvp = new MVP<>();
		this.controller = mvp.createController((V)this, modelClass);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		addWindowAncestorListener();
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
}
