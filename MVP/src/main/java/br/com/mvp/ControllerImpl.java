package br.com.mvp;

import java.util.List;

import javax.swing.JPanel;

import br.com.mvp.binding.Binding;

/**
 * 
 * @author renato.moreira
 *
 * @param <V>
 * @param <M>
 */
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

	/**
	 * {@inheritDoc}
	 */
	public V getView() {
		return view;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public M getModel() {
		return model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateModel() throws Exception{
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateView() throws Exception {
		for (Binding b: bindings)
			b.updateView();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Binding> getBindings() {
		return bindings;
	}
}
