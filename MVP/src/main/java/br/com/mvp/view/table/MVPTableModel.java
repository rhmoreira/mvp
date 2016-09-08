package br.com.mvp.view.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.mvp.view.table.mapper.ColumnMapper;
import br.com.mvp.view.table.mapper.ColumnValueResolver;
import br.com.mvp.view.table.mapper.TableMapper;

public class MVPTableModel<M> extends DefaultTableModel {

	private List<M> modelList;
	private TableMapper<M> mapper;
	
	public MVPTableModel(TableMapper<M> mapper) {
		this.mapper = mapper;
		this.modelList = new ArrayList<>();
		
		update();
	}
	
	public MVPTableModel(TableMapper<M> mapper, List<M> modelList) {
		this.mapper = mapper;
		this.modelList = new ArrayList<>(modelList);
		
		update();
	}

	public void update() {
		updateColumns(mapper.getMappers().size());
		updateData();		
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
				 .forEach(m -> { addRow(m); });
	}
	
	public void addRow(M modelData) {
		int columns = mapper.getMappers().size();
		Vector<Object> rowData = new Vector<>(columns);
		for (int i = 0; i < columns; i++)
			rowData.add(i, mapper.getColumnMapper(i).getValueResolver().getColumnValue(modelData));
		
		super.addRow(rowData);
		modelList.add(modelData);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return mapper.getColumnMapper(column).isEditable();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		M m = modelList.get(row);
		ColumnMapper<M> columnMapper = mapper.getColumnMapper(column);
		return columnMapper.getValueResolver().getColumnValue(m);
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);
		
		M m = modelList.get(row);
		ColumnMapper<M> columnMapper = mapper.getColumnMapper(column);
		columnMapper.getValueResolver().setColumnValue(m, aValue);
	}

	public Collection<M> getModelList() {
		return modelList;
	}
	
	public TableMapper<M> getMapper() {
		return mapper;
	}	
}
