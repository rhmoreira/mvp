package br.com.mvp;

import javax.swing.JPanel;

public class MVP<V extends JPanel, M> {
	
	public Controller<V, M> createController(Class<V> viewClass, Class<M> modelClass) throws Exception{
		
		Instrumentator<M> modelInst = InstrumentatorFactory.create(modelClass);
		M model = modelInst.get();
		
		Instrumentator<V> viewInst = InstrumentatorFactory.create(viewClass, modelInst.getScanner());
		V view = viewInst.get();
		
		Controller<V, M> controller = new ControllerImpl<>(view, model);		
		return controller;
	}
}
