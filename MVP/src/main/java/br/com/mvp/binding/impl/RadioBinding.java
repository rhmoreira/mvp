package br.com.mvp.binding.impl;

import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import br.com.mvp.binding.listener.RadioActionListener;
import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class RadioBinding extends ComponentBinding<Object, Converter<Object, String>> {
	
	private ActionListener listener;

	public RadioBinding(ViewModelBind bind, FieldMatch fieldMatch) throws Exception {
		super(bind, fieldMatch);
		listener = new RadioActionListener(bind, fieldMatch, getConverter());
	}
	
	@Override
	protected void finallyBind(Object component) throws Exception {
		RadioButtonManipulator<AbstractButton> manipulator = (t) -> t.addActionListener(listener);
		doAction(manipulator);
	}
	
	private void removeListeners() throws Exception{
		RadioButtonManipulator<AbstractButton> manipulator = (t) -> t.removeActionListener(listener);
		doAction(manipulator);
	}
	
	@Override
	public void updateView() throws Exception {
		RadioButtonManipulator<AbstractButton> manipulator = (t) -> {
			String value = (String) RadioBinding.this.getModelValue();
			if (t.getText().equals(value))
				t.setSelected(true);
			else
				t.setSelected(false);
		};
		doAction(manipulator);
	}

	@Override
	public void updateModel() throws Exception {
		RadioButtonManipulator<AbstractButton> manipulator = (t) -> RadioBinding.this.setModelValue(t.getText());
		doAction(manipulator);
	}

	@Override
	public void unbind() {
		try {
			removeListeners();
			super.unbind();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void doAction(RadioButtonManipulator<AbstractButton> manipulator) throws Exception{
		if (component instanceof ButtonGroup){
			Enumeration<AbstractButton> radioEnum = ((ButtonGroup)component).getElements();
			manipulator.manipulate(radioEnum);
		}else if (component instanceof JRadioButton)
			manipulator.manipulate(((JRadioButton)component));
	}
	
	@FunctionalInterface
	private interface RadioButtonManipulator<T>{
		public void manipulate(T t) throws Exception;
		
		default void manipulate(Enumeration<T> elements) throws Exception{
			while (elements.hasMoreElements())
				manipulate(elements.nextElement());
		}
	}
}
