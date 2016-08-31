package br.com.mvp.binding;

public interface Binding {

	void updateView() throws Exception;
	void updateModel() throws Exception;
	
	Object getView();
	Object getModel();
	
	void unbind();
}
