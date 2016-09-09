package br.com.mvp.binding.listener;

import javax.swing.JPanel;

import br.com.mvp.reflection.MemberHandler.MethodHandler;
import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public abstract class Listener<C extends Converter<?, ?>> {

	private ViewModelBind bind;
	protected FieldMatch fieldMatch;
	protected C converter;
	
	public Listener(ViewModelBind bind, FieldMatch fieldMatch, C converter) {
		super();
		this.bind = bind;
		this.fieldMatch = fieldMatch;
		this.converter = converter;
	}
	
	protected void updateModel(Object value) throws Exception{
		MethodHandler setter = bind.getModelClassHandler()
				.getMemberHandler()
				.setterMethodForField(fieldMatch.getModelField());
		
		value = ((Converter<Object, Object>)converter).fromView(value);
		setter.invoke(bind.getModel(), value);
	}
	
	protected JPanel getViewInstance(){
		return bind.getView();
	}
}
