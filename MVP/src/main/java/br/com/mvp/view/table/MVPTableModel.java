package br.com.mvp.view.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.mvp.view.table.mapper.ColumnMapper;
import br.com.mvp.view.table.mapper.TableMapper;

public class MVPTableModel<M> extends DefaultTableModel {

	private static final long serialVersionUID = 5328879409260042766L;
	
	private List<M> modelList;
	private TableMapper<M> mapper;
	
	public MVPTableModel(TableMapper<M> mapper) {
		this.mapper = mapper;
		this.modelList = new ArrayList<>();
		
		refresh();
	}
	
	public MVPTableModel(TableMapper<M> mapper, List<M> modelList) {
		this.mapper = mapper;
		this.modelList = new ArrayList<>(modelList);
		
		refresh();
	}

	private void refresh() {
		updateColumns(mapper.getMappers().size());
		updateData();
		
		fireTableDataChanged();
	}
	
	private void removeAll(){
		getDataVector().clear();
		
		fireTableDataChanged();
	}
	
	private void updateColumns(int columns){
		Vector<Object> columnNames = new Vector<>(columns);
		mapper.getMappers()
			  .stream()
			  .forEach(cm -> {
				  columnNames.add(cm.getColumnIndex(), cm.getColumnName());
			  });
		setColumnIdentifiers(columnNames);
	}
	
	private void updateData(){
		modelList.stream()
				 .forEach(m -> { addRowInternal(m); });
	}
	
<<<<<<< HEAD
	public void addRowInternal(M modelData) {
=======
	public void addRow(M modelData) {
		modelList.add(modelData);
		
>>>>>>> branch 'proxy' of https://github.com/rhmoreira/mvp
		int columns = mapper.getMappers().size();
		Vector<Object> rowData = new Vector<>(columns);
		for (int i = 0; i < columns; i++)
			rowData.add(i, mapper.getColumnMapper(i).getValueResolver().getColumnValue(modelData));
		
		super.addRow(rowData);
	}
	
<<<<<<< HEAD
	public void addRow(M modelData) {
		modelList.add(modelData);
		addRowInternal(modelData);
	}
	
=======
>>>>>>> branch 'proxy' of https://github.com/rhmoreira/mvp
	public void addRows(List<M> modelData) {
		modelData.stream().forEach(m -> addRow(m));
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return mapper.getColumnMapper(column).isEditable();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (getRowCount() == 0)
			return Object.class;
		
		return getValueAt(0, columnIndex).getClass();
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);
		
		M m = modelList.get(row);
		ColumnMapper<M> columnMapper = mapper.getColumnMapper(column);
		columnMapper.getValueResolver().setColumnValue(m, aValue);
	}
	
	@Override
	public void removeRow(int row) {
		super.removeRow(row);
		modelList.remove(row);
	}
	
	public void removeRows(int[] rows) {
		Arrays
			.stream(rows)
			.forEach(row -> removeRow(row));
	}

	public List<M> getModelList() {
		return modelList;
	}
	
	public void setModelList(List<M> newModels){
		removeAll();
		this.modelList = newModels;
		refresh();
	}
	
	public TableMapper<M> getMapper() {
		return mapper;
	}	
}
