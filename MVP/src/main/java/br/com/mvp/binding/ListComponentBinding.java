package br.com.mvp.binding;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.beanutils.ConstructorUtils;

import br.com.mvp.binding.listener.BindingSelectionListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.annotation.List;

public class ListComponentBinding extends ComponentBinding<JList<Object>> {

	private ListSelectionListener selectionListener;
	
	public ListComponentBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
	}

	@Override
	protected void finallyBind(JList<Object> component) throws Exception {
		List modelAnnotation = (List) fieldMatch.getModelAnnotation();
		DefaultListModel<? extends Object> listModel = ConstructorUtils
				.invokeConstructor(modelAnnotation.listModel(), new Object[]{});
		component.setModel((ListModel<Object>) listModel);
		
		this.selectionListener = new BindingSelectionListener(modelInstance, viewInstance, fieldMatch);
		component.addListSelectionListener(selectionListener);
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
