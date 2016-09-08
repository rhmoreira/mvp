package br.com.mvp.view.table.mapper;

public class ColumnMapperImpl<T> implements ColumnMapper<T> {

	private int index;
	private String columnName;
	private boolean editable;
	private ColumnValueResolver<T> resolver;
	
	public ColumnMapperImpl(int index, String columnName, boolean editable, ColumnValueResolver<T> resolver) {
		super();
		this.index = index;
		this.columnName = columnName;
		this.editable = editable;
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
	
	public boolean isEditable() {
		return editable;
	}

	@Override
	public ColumnValueResolver<T> getValueResolver() {
		return resolver;
	}

}
