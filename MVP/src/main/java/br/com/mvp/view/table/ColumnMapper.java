package br.com.mvp.view.table;

public interface ColumnMapper<T> {

	int getColumnIndex();
	String getColumnName();
	
	ColumnValueResolver<T> getValueResolver();
}
