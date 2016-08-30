package br.com.mvp.binding.listener;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import br.com.mvp.binding.impl.TextConverter;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.annotation.Text;

public class TextDocumentListener extends Listener implements DocumentListener {
	
	public TextDocumentListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) {
		super(modelInstance, viewInstance, fieldMatch);
	}

	public void insertUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	public void removeUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	@Override
	public void changedUpdate(DocumentEvent event) {
		try{
			Document document = event.getDocument();
			Object value = document.getText(0, document.getLength());
			
			Text text = (Text) fieldMatch.getModelAnnotation();
			TextConverter converter = new TextConverter(text.convertNumber().type(), text.convertNumber().decimalDigits(), value.toString());
			
			updateModel(converter.convert());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
