package br.com.mvp.binding.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class BindingCheckboxItemListener extends BindingListener implements ItemListener {

	public BindingCheckboxItemListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) {
		super(modelInstance, viewInstance, fieldMatch);
	}

	
	@Override
	public void itemStateChanged(ItemEvent event) {
		try{
			JCheckBox chck = (JCheckBox) event.getSource();
			updateModel(chck.isSelected());
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
