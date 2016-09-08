package br.com.mvp.binding.impl;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;

import br.com.mvp.binding.listener.TableListener;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.ListConverter;

public class TableBinding extends ComponentBinding<JTable, ListConverter<Object, Object>> {
	
	private TableModelListener listener;

	public TableBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
		listener = new TableListener(modelInstance, viewInstance, fieldMatch, getConverter());
	}

	@Override
	public void updateView() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void finallyBind(JTable component) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
