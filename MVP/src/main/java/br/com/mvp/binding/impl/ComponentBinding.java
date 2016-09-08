package br.com.mvp.binding.impl;

import java.lang.annotation.Annotation;

import javax.swing.JPanel;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.MethodUtils;

import br.com.mvp.binding.Binding;
import br.com.mvp.instrument.reflection.ReflectionUtils;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

@SuppressWarnings("unchecked")
public abstract class ComponentBinding<VC, C extends Converter<?, ?>> implements Binding {

	protected Object modelInstance;
	protected JPanel viewInstance;
	protected FieldMatch fieldMatch;
	protected VC component;
	protected C converter;

	public ComponentBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super();
		this.modelInstance = modelInstance;
		this.viewInstance = viewInstance;
		this.fieldMatch = fieldMatch;
		
		component = ReflectionUtils.getFieldValue(viewInstance, fieldMatch.getViewField());
		createConverter(fieldMatch.getModelAnnotation());
	}
	
	private void createConverter(Annotation a) throws Exception{
		final Object[] args = new Object[]{};
		Class<C> converterClass =  (Class<C>) MethodUtils.invokeMethod(a, "converter", args);
		converter = ConstructorUtils.invokeConstructor(converterClass, args);
	}
	
	public final void finallyBind() throws Exception{
		finallyBind(component);
	}
	
	protected abstract void finallyBind(VC component) throws Exception;

	@Override
	public Object getView(){
		return viewInstance;
	}

	@Override
	public Object getModel(){
		return modelInstance;
	}
	
	@Override
	public void unbind() {
		modelInstance = null;
		viewInstance = null;
		fieldMatch = null;
		component = null;
		converter = null;		
	}
	
	protected VC getComponent(){
		return component;
	}
	
	protected Object getModelValue() throws Exception{
		Object fieldValue = ReflectionUtils.getFieldValue(modelInstance, fieldMatch.getModelField());
		return ((Converter<Object, Object>)converter).fromModel(fieldValue);
	}
	
	protected void setModelValue(Object value) throws Exception{
		value = ((Converter<Object, Object>)converter).fromView(value);
		ReflectionUtils.setFieldValue(modelInstance, value, fieldMatch.getModelField());
	}

	public Object getModelInstance() {
		return modelInstance;
	}

	public JPanel getViewInstance() {
		return viewInstance;
	}

	public FieldMatch getFieldMatch() {
		return fieldMatch;
	}

	public C getConverter() {
		return converter;
	}
}
