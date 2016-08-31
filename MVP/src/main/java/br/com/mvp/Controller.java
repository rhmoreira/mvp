package br.com.mvp;

import java.util.List;

import javax.swing.JPanel;

import br.com.mvp.binding.Binding;

public interface Controller<V extends JPanel, M> {

	/**
	 * The reference to the view instance
	 * @return
	 */
	V getView();
	/**
	 * The reference to the proxied model instance
	 * @return
	 */
	M getModel();
	
	/**
	 * Update the mapped attributes in the model with the view components values
	 * @throws Exception
	 */
	void updateModel() throws Exception;
	
	/**
	 * Update the mapped attributes in the view with the model attributes values
	 * @throws Exception
	 */
	void updateView() throws Exception;
	
	/**
	 * The binding list, containing each bound view component to its correspondent mapped model attribute
	 * @throws Exception
	 */
	List<Binding> getBindings();
}
