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
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undoBinding() {
		// TODO Auto-generated method stub
		
	}

}
