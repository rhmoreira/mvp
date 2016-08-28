package br.com.mvp.binding;

import java.lang.reflect.Field;

import javax.swing.JPanel;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import br.com.mvp.binding.listener.BindingDocumentListener;
import br.com.mvp.instrument.reflection.ReflectionUtils;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class TextComponentBinding extends ComponentBinding<JTextComponent> {
	
	private DocumentListener documentListener;

	public TextComponentBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
		
		addListener();
	}

	private void addListener() throws Exception {
		Field viewField = fieldMatch.getViewField();
		JTextComponent textComp = ReflectionUtils.getFieldValue(viewInstance, viewField);
		
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
