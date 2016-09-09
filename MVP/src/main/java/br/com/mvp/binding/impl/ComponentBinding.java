package br.com.mvp.binding.impl;

import java.lang.annotation.Annotation;

import javax.swing.JPanel;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.MethodUtils;

import br.com.mvp.binding.Binding;
import br.com.mvp.reflection.ReflectionUtils;
import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.annotation.ViewTable;
import br.com.mvp.view.converter.Converter;

@SuppressWarnings("unchecked")
public abstract class ComponentBinding<VC, C extends Converter<?, ?>> implements Binding {

	private ViewModelBind bind;
	protected FieldMatch fieldMatch;
	protected VC component;
	protected C converter;

	public ComponentBinding(ViewModelBind bind, FieldMatch fieldMatch) throws Exception {
		super();
		this.bind = bind;
		this.fieldMatch = fieldMatch;
		
		component = ReflectionUtils.getFieldValue(bind.getView(), fieldMatch.getViewField());
		createConverter(fieldMatch.getModelAnnotation());
	}
	
	private void createConverter(Annotation a) throws Exception{
		if ( !(a instanceof ViewTable) ){
			final Object[] args = new Object[]{};
			Class<C> converterClass =  (Class<C>) MethodUtils.invokeMethod(a, "converter", args);
			converter = ConstructorUtils.invokeConstructor(converterClass, args);
		}
	}
	
	public final void finallyBind() throws Exception{
		finallyBind(component);
	}
	
	protected abstract void finallyBind(VC component) throws Exception;

	@Override
	public Object getView(){
		return bind.getView();
	}

	@Override
	public Object getModel(){
		return bind.getModel();
	}
	
	@Override
	public void unbind() {
		bind.unbind();
		fieldMatch = null;
		component = null;
		converter = null;		
	}
	
	protected VC getComponent(){
		return component;
	}
	
	protected Object getModelValue() throws Exception{
		Object fieldValue = ReflectionUtils.getFieldValue(bind.getModel(), fieldMatch.getModelField());
		if (converter != null)
			return ((Converter<Object, Object>)converter).fromModel(fieldValue);
		else
			return fieldValue;
	}
	
	protected void setModelValue(Object value) throws Exception{
		if (converter != null)
			value = ((Converter<Object, Object>)converter).fromView(value);
		ReflectionUtils.setFieldValue(bind.getModel(), value, fieldMatch.getModelField());
	}

	public Object getModelInstance() {
		return bind.getModel();
	}

	public JPanel getViewInstance() {
		return bind.getView();
	}

	public FieldMatch getFieldMatch() {
		return fieldMatch;
	}

	public C getConverter() {
		return converter;
	}
}
