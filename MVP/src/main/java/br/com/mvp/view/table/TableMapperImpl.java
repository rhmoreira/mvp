package br.com.mvp.view.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TableMapperImpl<T> implements TableMapper<T> {
	
	private List<ColumnMapper<T>> mappers = new ArrayList<>();
	private Map<String, ColumnMapper<T>> mappersMap = new HashMap<>();

	@Override
	public Collection<ColumnMapper<T>> getMappers() {
		return mappers;
	}
	
	int addMapper(ColumnMapper<T> mapper){
		mappers.add(mapper);
		mappersMap.put(mapper.getColumnName(), mapper);
		return mappers.size();
	}

	public ColumnMapper<T> getColumnMapper(String columnName) {
		return mappersMap.get(columnName);
	}

	public ColumnMapper<T> getColumnMapper(int columnIndex) {
		return mappers.get(columnIndex);
	}

}
