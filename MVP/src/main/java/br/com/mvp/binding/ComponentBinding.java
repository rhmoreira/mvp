package br.com.mvp.binding;

import java.awt.Component;

import javax.swing.JPanel;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public abstract class ComponentBinding<C extends Component> implements Binding {

	protected Object modelInstance;
	protected JPanel viewInstance;
	protected FieldMatch fieldMatch;

	public ComponentBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) {
		super();
		this.modelInstance = modelInstance;
		this.viewInstance = viewInstance;
		this.fieldMatch = fieldMatch;
	}

	@Override
	public Object getView() {
		return viewInstance;
	}

	@Override
	public Object getModel(){
		return modelInstance;
	}
}
