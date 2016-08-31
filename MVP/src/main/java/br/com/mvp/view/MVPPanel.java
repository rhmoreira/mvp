package br.com.mvp.view;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.mvp.Controller;
import br.com.mvp.MVP;
import br.com.mvp.instrument.reflection.ReflectionUtils;

public abstract class MVPPanel<V extends JPanel, M> extends JPanel{
	
	private static final long serialVersionUID = 5642290959402301950L;
	
	private Controller<V, M> controller;
	private Class<M> modelClass;
	
	public MVPPanel(Class<M> modelClass) {
		this.modelClass = modelClass;
	}
	
	protected Controller<V, M> getController() throws Exception{
		if (controller == null)
			createController();
		return controller;
	}
	
	protected void loadModel(M model) throws Exception{
		ReflectionUtils.copyProperties(model, getController().getModel());
		updateView();
	}
	
	protected void updateView() throws Exception{
		getController().updateView();
	}
	
	protected void updateModel() throws Exception{
		getController().updateModel();
	}
	
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
