package br.com.mvp.binding.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class RadioActionListener extends Listener implements ActionListener {

	public RadioActionListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) {
		super(modelInstance, viewInstance, fieldMatch);
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