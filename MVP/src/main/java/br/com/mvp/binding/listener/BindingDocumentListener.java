package br.com.mvp.binding.listener;

import java.lang.reflect.Field;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import org.apache.commons.beanutils.ConstructorUtils;

import br.com.mvp.binding.Bind;
import br.com.mvp.instrument.reflection.ClassHandler;
import br.com.mvp.instrument.reflection.MemberHandler.MethodHandler;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.annotation.Numeric;

public class BindingDocumentListener implements DocumentListener {

	private Object modelInstance;
	private JPanel viewInstance;
	private FieldMatch fieldMatch;
	
	private ClassHandler modelClassHandler;
	
	public BindingDocumentListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) {
		super();
		this.modelInstance = modelInstance;
		this.viewInstance = viewInstance;
		this.fieldMatch = fieldMatch;
		this.modelClassHandler = ((Bind) modelInstance).getClassHandler();
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
			Field modelField = fieldMatch.getModelField();
			Document document = event.getDocument();
			Object value = document.getText(0, document.getLength());
			
			if (fieldMatch.getModelAnnotation().annotationType() == Numeric.class)
				value = ConstructorUtils.invokeConstructor(modelField.getType(), value);
			
			MethodHandler setter = modelClassHandler.getMemberHandler().setterMethodForField(modelField);
			setter.invoke(modelInstance, value);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
