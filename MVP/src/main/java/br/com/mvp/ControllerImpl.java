package br.com.mvp;

import java.util.List;

import javax.swing.JPanel;

import br.com.mvp.binding.Binding;

class ControllerImpl<V extends JPanel, M> implements Controller<V, M> {

	private V view;
	private M model;
	private List<Binding> bindings;
	
	public ControllerImpl(V view, M model, List<Binding> bindings) {
		super();
		this.view = view;
		this.model = model;
		this.bindings = bindings;
	}

	public V getView() {
		return view;
	}

	@Override
	public M getModel() {
		return model;
	}

	public List<Binding> getBindings() {
		return bindings;
	}
}
