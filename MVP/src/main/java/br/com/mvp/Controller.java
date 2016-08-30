package br.com.mvp;

import javax.swing.JPanel;

public interface Controller<V extends JPanel, M> {

	V getView();
	M getModel();
	
	void updateModel() throws Exception;
	void updateView() throws Exception;
}
