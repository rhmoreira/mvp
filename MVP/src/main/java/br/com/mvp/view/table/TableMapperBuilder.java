package br.com.mvp.view.table;

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
	
	public TableMapperBuilder<T> addColumn(String columnName, ColumnValueResolver<T> valueResolver){
		ColumnMapper<T> cm = new ColumnMapperImpl<>(currentColumnIndex, columnName, valueResolver);
		currentColumnIndex += tableMapper.addMapper(cm);
		return this;
	}
	
	public TableMapper<T> build(){
		if (tableMapper.getMappers().isEmpty())
			throw new RuntimeException("No columns were added to the mapper");
		
		return tableMapper;
	}
	
}
