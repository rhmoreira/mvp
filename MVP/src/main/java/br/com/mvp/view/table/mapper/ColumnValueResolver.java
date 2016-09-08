package br.com.mvp.view.table.mapper;

public interface ColumnValueResolver<T> {

	Object getColumnValue(T target);
	
	void setColumnValue(T target, Object value);
}
