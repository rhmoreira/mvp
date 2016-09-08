package br.com.mvp.view.table;

public interface ColumnValueResolver<T> {

	Object getColumnValue(T target);
}
