package br.com.mvp.view.table.mapper;

public interface ColumnMapper<T> {

	int getColumnIndex();
	String getColumnName();
	boolean isEditable();
	
	ColumnValueResolver<T> getValueResolver();
}
