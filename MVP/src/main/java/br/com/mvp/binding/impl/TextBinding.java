package br.com.mvp.binding.impl;

import javax.swing.JPanel;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import br.com.mvp.binding.listener.TextDocumentListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.annotation.Text;

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
		setModelValue(getComponentValue());
	}
	
	private Object getComponentValue(){
		JTextComponent textComponent = getComponent();
		Object value = textComponent.getText();
		Text text = (Text) fieldMatch.getModelAnnotation();
		TextConverter converter = new TextConverter(text.convertNumber().type(), text.convertNumber().decimalDigits(), value.toString());
		
		return converter.convert();
	}

	@Override
	public void undoBinding() {
		// TODO Auto-generated method stub
		
	}

}
