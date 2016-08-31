package br.com.mvp.binding.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class RadioActionListener extends Listener<Converter<Object, String>> implements ActionListener {

	public RadioActionListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch, Converter<Object, String> converter) {
		super(modelInstance, viewInstance, fieldMatch, converter);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try{
			JRadioButton radio = (JRadioButton) event.getSource();
			String text = radio.getText();
			updateModel(text);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}