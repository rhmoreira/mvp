package br.com.mvp.binding.impl;

import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.com.mvp.binding.listener.RadioActionListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class RadioBinding extends ComponentBinding<Object> {
	
	private ActionListener listener;

	public RadioBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
		listener = new RadioActionListener(modelInstance, viewInstance, fieldMatch);
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
	public void undoBinding() {
	}
}
