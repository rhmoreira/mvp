package br.com.mvp.binding;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class ListComponentBinding extends ComponentBinding<JList<? extends DefaultListModel<?>>> {

	public ListComponentBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
	}

	@Override
	protected void finallyBind(JList<? extends DefaultListModel<?>> component) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undoBinding() {
		// TODO Auto-generated method stub
		
	}

	

}
