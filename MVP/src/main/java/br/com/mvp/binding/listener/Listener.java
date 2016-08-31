package br.com.mvp.binding.listener;

import javax.swing.JPanel;

import br.com.mvp.binding.Bind;
import br.com.mvp.instrument.reflection.ClassHandler;
import br.com.mvp.instrument.reflection.MemberHandler.MethodHandler;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public abstract class Listener<C extends Converter<?, ?>> {

	protected Object modelInstance;
	protected JPanel viewInstance;
	protected FieldMatch fieldMatch;
	protected C converter;
	
	protected ClassHandler modelClassHandler;
	
	public Listener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch, C converter) {
		super();
		this.modelInstance = modelInstance;
		this.viewInstance = viewInstance;
		this.fieldMatch = fieldMatch;
		this.converter = converter;
		this.modelClassHandler = ((Bind) modelInstance).getClassHandler();
	}
	
	protected void updateModel(Object value) throws Exception{
		MethodHandler setter = modelClassHandler
				.getMemberHandler()
				.setterMethodForField(fieldMatch.getModelField());
		setter.invoke(modelInstance, value);
	}
}
