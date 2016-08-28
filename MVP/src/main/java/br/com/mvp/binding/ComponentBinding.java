package br.com.mvp.binding;

import java.awt.Component;

import javax.swing.JPanel;

import br.com.mvp.instrument.reflection.ReflectionUtils;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

@SuppressWarnings("unchecked")
public abstract class ComponentBinding<C extends Component> implements Binding {

	protected Object modelInstance;
	protected JPanel viewInstance;
	protected FieldMatch fieldMatch;

	public ComponentBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super();
		this.modelInstance = modelInstance;
		this.viewInstance = viewInstance;
		this.fieldMatch = fieldMatch;
		
		finallyBind((C) ReflectionUtils.getFieldValue(viewInstance, fieldMatch.getViewField()));
	}
	
	protected abstract void finallyBind(C component);

	@Override
	public Object getView() {
		return viewInstance;
	}

	@Override
	public Object getModel(){
		return modelInstance;
	}
}
