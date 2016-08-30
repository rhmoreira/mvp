package br.com.mvp.binding.impl;

import javax.swing.JPanel;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import br.com.mvp.binding.listener.TextDocumentListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class TextBinding extends ComponentBinding<JTextComponent> {
	
	private DocumentListener documentListener;

	public TextBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
		this.documentListener = new TextDocumentListener(modelInstance, viewInstance, fieldMatch);
	}

	protected void finallyBind(JTextComponent textComp){
		textComp.getDocument().addDocumentListener(this.documentListener);
	}

	@Override
	public void updateView() throws Exception {
		JTextComponent textComponent = getComponent();
		Object value = getModelValue();
		textComponent.setText(value == null ? null : value.toString());
	}

	@Override
	public void updateModel() throws Exception {
		JTextComponent textComponent = getComponent();
		setModelValue(textComponent.getText());
	}

	@Override
	public void undoBinding() {
		// TODO Auto-generated method stub
		
	}

}
