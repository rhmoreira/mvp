package br.com.mvp.binding.impl;

import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import br.com.mvp.binding.listener.CheckboxItemListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class CheckboxBinding extends ComponentBinding<JCheckBox> {

	private ItemListener itemListener;
	
	public CheckboxBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
		this.itemListener = new CheckboxItemListener(modelInstance, viewInstance, fieldMatch);
	}

	@Override
	protected void finallyBind(JCheckBox component) {
		component.addItemListener(this.itemListener);
	}
	
	@Override
	public void updateView() throws Exception {
		JCheckBox checkBox = getComponent();
		Object value = getModelValue();
		checkBox.setSelected(value != null ? (Boolean) value : false);
	}

	@Override
	public void updateModel() throws Exception{
		JCheckBox checkBox = getComponent();
		setModelValue(checkBox.isSelected());
	}

	@Override
	public void undoBinding() {
		// TODO Auto-generated method stub
		
	}

}
