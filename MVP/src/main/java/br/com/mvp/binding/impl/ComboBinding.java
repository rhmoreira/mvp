package br.com.mvp.binding.impl;

import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import br.com.mvp.binding.listener.ComboItemListener;
import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class ComboBinding extends ComponentBinding<JComboBox<Object>, Converter<Object, Object>> {

	private ItemListener listener;
	
	public ComboBinding(ViewModelBind bind, FieldMatch fieldMatch) throws Exception {
		super(bind, fieldMatch);
		this.listener = new ComboItemListener(bind, fieldMatch, converter);
	}

	@Override
	protected void finallyBind(JComboBox<Object> component) throws Exception {
		component.addItemListener(listener);
	}
	
	@Override
	public void updateView() throws Exception{
		JComboBox<Object> comboBox = getComponent();
		comboBox.setSelectedItem(getModelValue());
	}

	@Override
	public void updateModel() throws Exception{
		JComboBox<Object> comboBox = getComponent();
		setModelValue(comboBox.getSelectedItem());
	}

	@Override
	public void unbind() {
		getComponent().removeItemListener(listener);
		super.unbind();		
	}
}
