package br.com.mvp.binding;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class RadioComponentBinding extends ComponentBinding<JRadioButton> {

	public RadioComponentBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
	}
	
	@Override
	protected void finallyBind(JRadioButton component) {
	}

	@Override
	public void updateView() {
	}

	@Override
	public void updateModel() {
	}

	@Override
	public void undoBinding() {
	}
}
