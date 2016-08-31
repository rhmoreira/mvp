package br.com.mvp.binding.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class ComboItemListener extends Listener<Converter<Object, Object>> implements ItemListener {

	public ComboItemListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch, Converter<Object, Object> converter) {
		super(modelInstance, viewInstance, fieldMatch, converter);
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		try{
			if (event.getStateChange() == ItemEvent.SELECTED)
				updateModel(converter.fromView(event.getItem()));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
