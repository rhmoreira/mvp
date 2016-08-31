package br.com.mvp.binding.impl;

import javax.swing.JPanel;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import br.com.mvp.binding.listener.TextDocumentListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class TextBinding extends ComponentBinding<JTextComponent, Converter<Object, String>> {
	
	private DocumentListener documentListener;

	public TextBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
		this.documentListener = new TextDocumentListener(modelInstance, viewInstance, fieldMatch, getConverter());
	}

	protected void finallyBind(JTextComponent textComp){
		textComp.getDocument().addDocumentListener(this.documentListener);
	}

	@Override
	public void updateView() throws Exception {
		JTextComponent textComponent = getComponent();
		Object value = getModelValue();
		textComponent.setText((String) getConverter().fromModel(value));
	}

	@Override
	public void updateModel() throws Exception {
		JTextComponent textComponent = getComponent();
		String value = textComponent.getText();
		setModelValue(getConverter().fromView(value));
	}
	
	@Override
	public void unbind() {
		getComponent().getDocument().removeDocumentListener(documentListener);
		super.unbind();
	}

}
