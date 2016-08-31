package br.com.mvp.binding.impl;

import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.com.mvp.binding.listener.RadioActionListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class RadioBinding extends ComponentBinding<Object, Converter<Object, String>> {
	
	private ActionListener listener;

	public RadioBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
		listener = new RadioActionListener(modelInstance, viewInstance, fieldMatch, getConverter());
	}
	
	@Override
	protected void finallyBind(Object component) {
		if (component instanceof ButtonGroup){
			Enumeration<AbstractButton> radioEnum = ((ButtonGroup)component).getElements();
			configureListener(radioEnum);
		}else if (component instanceof JRadioButton)
			configureListener(((JRadioButton)component));
	}
	
	private <B extends AbstractButton> void configureListener(Enumeration<B> btnEnum){
		while (btnEnum.hasMoreElements())
			configureListener(btnEnum.nextElement());
	}
	
	private <B extends AbstractButton> void configureListener(B button){
		button.addActionListener(listener);
	}
	
	@Override
	public void updateView() {
	}

	@Override
	public void updateModel() {
	}

	@Override
	public void unbind() {
		removeListeners();
		super.unbind();
	}
	
	private void removeListeners(){
		if (component instanceof ButtonGroup){
			Enumeration<AbstractButton> radioEnum = ((ButtonGroup)component).getElements();
			removeListener(radioEnum);
		}else if (component instanceof JRadioButton)
			removeListener(((JRadioButton)component));
	}
	
	private <B extends AbstractButton> void removeListener(Enumeration<B> buttons){
		while (buttons.hasMoreElements())
			removeListener(buttons.nextElement());
	}
	
	private <B extends AbstractButton> void removeListener(B button){
		button.removeActionListener(listener);
	}
}
