package br.com.mvp.view.table.mapper;

import java.util.Collection;

public interface TableMapper<T> {

	Collection<ColumnMapper<T>> getMappers();
	ColumnMapper<T> getColumnMapper(String columnName);
	ColumnMapper<T> getColumnMapper(int columnIndex);
}
