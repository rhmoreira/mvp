package br.com.mvp.view.table.mapper;

public class TableMapperBuilder<T> {

	private TableMapperImpl<T> tableMapper;
	private int currentColumnIndex;
	
	private TableMapperBuilder(){}
	
	public static <ST> TableMapperBuilder<ST> newMapper(){
		TableMapperImpl<ST> tableMapper = new TableMapperImpl<>();
		
		TableMapperBuilder<ST> instance = new TableMapperBuilder<ST>();
		instance.tableMapper = tableMapper;
		
		return instance;
	}
	
	public TableMapperBuilder<T> addColumn(String columnName, boolean editable, ColumnValueResolver<T> valueResolver){
		ColumnMapper<T> cm = new ColumnMapperImpl<>(currentColumnIndex, columnName, editable, valueResolver);
		currentColumnIndex = tableMapper.addMapper(cm);
		return this;
	}
	
	public TableMapperBuilder<T> addColumn(String columnName, ColumnValueResolver<T> valueResolver){
		return addColumn(columnName, false, valueResolver);
	}
	
	public TableMapper<T> build(){
		if (tableMapper.getMappers().isEmpty())
			throw new RuntimeException("No columns were added to the mapper");
		
		return tableMapper;
	}
	
}
