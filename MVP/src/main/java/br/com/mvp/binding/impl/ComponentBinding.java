package br.com.mvp.binding.impl;

import javax.swing.JPanel;

import br.com.mvp.binding.Binding;
import br.com.mvp.instrument.reflection.ReflectionUtils;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public abstract class ComponentBinding<C> implements Binding {

	protected Object modelInstance;
	protected JPanel viewInstance;
	protected FieldMatch fieldMatch;
	protected C component;

	public ComponentBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super();
		this.modelInstance = modelInstance;
		this.viewInstance = viewInstance;
		this.fieldMatch = fieldMatch;
		
		component = ReflectionUtils.getFieldValue(viewInstance, fieldMatch.getViewField());
	}
	
	public final void finallyBind() throws Exception{
		finallyBind(component);
	}
	
	protected abstract void finallyBind(C component) throws Exception;

	@Override
	public Object getView() {
		return viewInstance;
	}

	@Override
	public Object getModel(){
		return modelInstance;
	}
	
	protected C getComponent(){
		return component;
	}
}
