package br.com.mvp.binding;

import javax.swing.JPanel;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import br.com.mvp.binding.listener.BindingDocumentListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class TextComponentBinding extends ComponentBinding<JTextComponent> {
	
	private DocumentListener documentListener;

	public TextComponentBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
	}

	protected void finallyBind(JTextComponent textComp){
		this.documentListener = new BindingDocumentListener(modelInstance, viewInstance, fieldMatch);
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
