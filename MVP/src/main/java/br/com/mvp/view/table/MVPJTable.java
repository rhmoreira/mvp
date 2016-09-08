package br.com.mvp.view.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.TableRowSorter;

import br.com.mvp.view.table.mapper.TableMapper;

public class MVPJTable<M> extends JTable {

	public MVPJTable(TableMapper<M> mapper) {
		this(mapper, new ArrayList<>());
	}
	
	public MVPJTable(TableMapper<M> mapper, List<M> modelList) {
		super(new MVPTableModel<>(mapper, modelList));
	}
	
	public void setDefaultComboBoxRenderer(Class<?> clazz, Object[] values){
		setDefaultRenderer(clazz, new ComboBoxCellRenderer(new JComboBox<>(values)));
		setDefaultEditor(clazz, new DefaultCellEditor(new JComboBox<>(values)));
	}
	
	@Override
	public void sorterChanged(RowSorterEvent e) {
		TableRowSorter<MVPTableModel<M>> rowSorter = (TableRowSorter<MVPTableModel<M>>) e.getSource();
		MVPTableModel<M> model = rowSorter.getModel();
		
		SortKey sortKey = rowSorter.getSortKeys().get(0);
		Collection<M> modelList = model.getModelList();
		
		Collections.sort(modelList);
		
		super.sorterChanged(e);
	}
}
