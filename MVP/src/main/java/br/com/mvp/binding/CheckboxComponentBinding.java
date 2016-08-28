package br.com.mvp.binding;

import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import br.com.mvp.binding.listener.BindingCheckboxItemListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class CheckboxComponentBinding extends ComponentBinding<JCheckBox> {

	private ItemListener itemListener;
	
	public CheckboxComponentBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
	}

	@Override
	protected void finallyBind(JCheckBox component) {
		this.itemListener = new BindingCheckboxItemListener(modelInstance, viewInstance, fieldMatch);
		component.addItemListener(this.itemListener);
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
