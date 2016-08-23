package br.com.mvp;

import javax.swing.JPanel;

class ControllerImpl<V extends JPanel, M> implements Controller<V, M> {

	private V view;
	private M model;
	
	public ControllerImpl(V view, M model) {
		super();
		this.view = view;
		this.model = model;
	}

	public V getView() {
		return view;
	}

	@Override
	public M getModel() {
		return model;
	}
}
