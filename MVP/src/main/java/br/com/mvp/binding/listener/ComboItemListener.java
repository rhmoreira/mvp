package br.com.mvp.binding.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class ComboItemListener extends Listener<Converter<Object, Object>> implements ItemListener {

	public ComboItemListener(ViewModelBind bind, FieldMatch fieldMatch, Converter<Object, Object> converter) {
		super(bind, fieldMatch, converter);
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		try{
			if (event.getStateChange() == ItemEvent.SELECTED)
				updateModel(event.getItem());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
