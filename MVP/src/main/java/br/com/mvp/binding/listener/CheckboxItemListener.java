package br.com.mvp.binding.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class CheckboxItemListener extends Listener<Converter<Object, Boolean>> implements ItemListener {

	public CheckboxItemListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch, Converter<Object, Boolean> converter) {
		super(modelInstance, viewInstance, fieldMatch, converter);
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
