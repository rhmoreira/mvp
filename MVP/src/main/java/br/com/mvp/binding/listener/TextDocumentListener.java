package br.com.mvp.binding.listener;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class TextDocumentListener extends Listener<Converter<Object, String>> implements DocumentListener {
	
	public TextDocumentListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch, Converter<Object, String> converter) {
		super(modelInstance, viewInstance, fieldMatch, converter);
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
			String value = document.getText(0, document.getLength());
			updateModel(converter.fromView(value));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
