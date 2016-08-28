package br.com.mvp.binding;

public interface Binding {

	void updateView();
	void updateModel();
	
	Object getView();
	Object getModel();
	
	void undoBinding();
}
