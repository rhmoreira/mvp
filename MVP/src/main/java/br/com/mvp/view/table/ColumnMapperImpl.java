package br.com.mvp.view.table;

public class ColumnMapperImpl<T> implements ColumnMapper<T> {

	private int index;
	private String columnName;
	private ColumnValueResolver<T> resolver;
	
	public ColumnMapperImpl(int index, String columnName, ColumnValueResolver<T> resolver) {
		super();
		this.index = index;
		this.columnName = columnName;
		this.resolver = resolver;
	}

	@Override
	public int getColumnIndex() {
		return index;
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public ColumnValueResolver<T> getValueResolver() {
		return resolver;
	}

}
