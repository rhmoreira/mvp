package br.com.mvp.view.table;

import java.util.Collection;

import javax.swing.table.DefaultTableModel;

public class MVPTableModel<M> extends DefaultTableModel {

	private Collection<M> modelList;
	private TableMapper<M> mapper;
	
	public MVPTableModel(TableMapper<M> mapper, Collection<M> modelList) {
		this.mapper = mapper;
		this.modelList = modelList;
		
		update();
	}

	public void update() {
		int rows = modelList.size();
		int columns = mapper.getMappers().size();
		
		Object[] columnIdentifiers = identifyColumns(columns);
		updateData(rows, columns, columnIdentifiers);		
	}
	
	private Object[] identifyColumns(int columns){
		Object[] columnNames = new Object[columns];
		mapper.getMappers()
			  .stream()
			  .forEach(cm -> {
				  columnNames[cm.getColumnIndex()] = cm.getColumnName();
			  });
		return columnNames;
	}
	
	private void updateData(int rows, int columns, Object[] columnIdentifiers){
		Object[][] data = new Object[rows][columns];
		int i = 0;
		for (M model: modelList)
			for (ColumnMapper<M> cm: mapper.getMappers())
				data[i++][cm.getColumnIndex()] = cm.getValueResolver().getColumnValue(model);
		
		setDataVector(data, columnIdentifiers);
	}

	public Collection<M> getModelList() {
		return modelList;
	}
	
	public TableMapper<M> getMapper() {
		return mapper;
	}	
}
