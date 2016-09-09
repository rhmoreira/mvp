package br.com.mvp.binding.impl;

import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import br.com.mvp.binding.listener.TextDocumentListener;
import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class TextBinding extends ComponentBinding<JTextComponent, Converter<Object, String>> {
	
	private DocumentListener documentListener;

	public TextBinding(ViewModelBind bind, FieldMatch fieldMatch) throws Exception {
		super(bind, fieldMatch);
		this.documentListener = new TextDocumentListener(bind, fieldMatch, getConverter());
	}

	protected void finallyBind(JTextComponent textComp){
		textComp.getDocument().addDocumentListener(this.documentListener);
	}

	@Override
	public void updateView() throws Exception {
		JTextComponent textComponent = getComponent();
		textComponent.setText((String) getModelValue());
	}

	@Override
	public void updateModel() throws Exception {
		JTextComponent textComponent = getComponent();
		String value = textComponent.getText();
		setModelValue(value);
	}
	
	@Override
	public void unbind() {
		getComponent().getDocument().removeDocumentListener(documentListener);
		super.unbind();
	}

}
