package br.com.mvp.binding;

import java.awt.Component;

public class ComponentBinding<C extends Component> implements Binding {
	
	

	@Override
	public void updateView() {
	}

	@Override
	public void updateModel() {
	}

	@Override
	public Bind getViewBind() {
		return null;
	}

	@Override
	public Bind getModelBind() {
		return null;
	}

	@Override
	public void undoBinding() {

	}

}
