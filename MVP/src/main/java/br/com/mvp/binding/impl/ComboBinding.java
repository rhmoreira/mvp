package br.com.mvp.binding.impl;

import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import br.com.mvp.binding.listener.ComboItemListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class ComboBinding extends ComponentBinding<JComboBox<Object>> {

	private ItemListener listener;
	
	public ComboBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
		this.listener = new ComboItemListener(modelInstance, viewInstance, fieldMatch);
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
	public void undoBinding() {
		// TODO Auto-generated method stub
		
	}
}
