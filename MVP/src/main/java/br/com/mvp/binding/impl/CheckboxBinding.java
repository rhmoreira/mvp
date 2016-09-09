package br.com.mvp.binding.impl;

import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import br.com.mvp.binding.listener.CheckboxItemListener;
import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.Converter;

public class CheckboxBinding extends ComponentBinding<JCheckBox, Converter<Object, Boolean>> {

	private ItemListener itemListener;
	
	public CheckboxBinding(ViewModelBind bind, FieldMatch fieldMatch) throws Exception {
		super(bind, fieldMatch);
		this.itemListener = new CheckboxItemListener(bind, fieldMatch, converter);
	}

	@Override
	protected void finallyBind(JCheckBox component) {
		component.addItemListener(this.itemListener);
	}
	
	@Override
	public void updateView() throws Exception {
		JCheckBox checkBox = getComponent();
		Object value = getModelValue();
		checkBox.setSelected( value == null ? false : (Boolean) value);
	}

	@Override
	public void updateModel() throws Exception{
		JCheckBox checkBox = getComponent();
		setModelValue(checkBox.isSelected());
	}

	@Override
	public void unbind() {
		getComponent().removeItemListener(itemListener);
		super.unbind();
	}

}
