package br.com.mvp.binding.impl;

import java.awt.Component;

import javax.swing.JPanel;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class NumericBinding extends ComponentBinding<Component> {

	public NumericBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
	}
	
	@Override
	protected void finallyBind(Component component) {
		
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
