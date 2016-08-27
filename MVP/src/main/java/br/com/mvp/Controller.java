package br.com.mvp;

import java.util.List;

import javax.swing.JPanel;

import br.com.mvp.binding.Binding;

public interface Controller<V extends JPanel, M> {

	V getView();
	M getModel();
	
	List<Binding> getBindings();
}
