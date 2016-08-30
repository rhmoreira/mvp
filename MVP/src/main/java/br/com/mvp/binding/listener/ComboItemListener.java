package br.com.mvp.binding.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class ComboItemListener extends Listener implements ItemListener {

	public ComboItemListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) {
		super(modelInstance, viewInstance, fieldMatch);
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
