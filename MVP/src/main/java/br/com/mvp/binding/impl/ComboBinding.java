package br.com.mvp.binding.impl;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class ComboBinding extends ComponentBinding<JComboBox<? extends DefaultComboBoxModel<?>>> {

	public ComboBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
	}

	@Override
	protected void finallyBind(JComboBox<? extends DefaultComboBoxModel<?>> component) {
		// TODO Auto-generated method stub
		
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
