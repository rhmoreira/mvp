package br.com.mvp.binding;

public interface Binding {

	void updateView();
	void updateModel();
	
	Bind getViewBind();
	Bind getModelBind();
	
	void undoBinding();
}
