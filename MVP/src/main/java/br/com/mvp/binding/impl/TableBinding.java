package br.com.mvp.binding.impl;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.ListConverter;
import br.com.mvp.view.table.MVPJTable;
import br.com.mvp.view.table.MVPTableModel;

public class TableBinding extends ComponentBinding<JTable, ListConverter<Object, Object>> {

	private boolean valid = true;
	
	public TableBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
	}

	@Override
	public void updateView() throws Exception {
		if (valid){
			MVPJTable<Object> table = (MVPJTable<Object>) getComponent();
			MVPTableModel<Object> tableModel = table.getModel();
			
			Object modelValue = getModelValue();
			tableModel.setModelList(List.class.cast(modelValue));
		}
	}

	@Override
	public void updateModel() throws Exception {
		if (valid){
			MVPJTable<Object> table = (MVPJTable<Object>) getComponent();
			List<Object> modelList = table.getModel().getModelList();
			setModelValue(modelList);
		}
	}

	@Override
	protected void finallyBind(JTable table) throws Exception {
		if ( !(table instanceof MVPJTable) ){
			System.out.println("The mapped attribute @ViewTable '" + fieldMatch.getModelField().getName() + 
					"' does not map to a MVPTable type. MVP only bind models to MVPTable types.");
			
			valid = false;
		}
	}

}
