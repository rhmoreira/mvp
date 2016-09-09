package br.com.mvp.binding.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class CheckboxItemListener extends Listener<Converter<Object, Boolean>> implements ItemListener {

	public CheckboxItemListener(ViewModelBind bind, FieldMatch fieldMatch, Converter<Object, Boolean> converter) {
		super(bind, fieldMatch, converter);
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
