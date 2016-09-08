package br.com.mvp.view.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;

import br.com.mvp.view.table.mapper.TableMapper;

public class MVPJTable<M> extends JTable {

	public MVPJTable(TableMapper<M> mapper) {
		this(mapper, new ArrayList<>());
	}
	
	public MVPJTable(TableMapper<M> mapper, List<M> modelList) {
		super(new MVPTableModel<>(mapper, modelList));
	}
	
	public void setDefaultComboBoxRenderer(Class<?> clazz, Object[] values){
		ComboBoxCellRenderer comboRenderer = new ComboBoxCellRenderer(values);
		setDefaultRenderer(clazz, comboRenderer);
		setDefaultEditor(clazz, new DefaultCellEditor(comboRenderer));
	}
}
