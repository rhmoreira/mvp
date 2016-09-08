package br.com.mvp.view.table.mapper;

public class ValueResolverBuilder {

	private ValueResolverBuilder(){}
	
	public static <ST> ColumnValueResolver<ST> newValueResolver(ValueRetriever<ST> retriever, ValueDefiner<ST> definer){
		ColumnValueResolver<ST> valueResolver = new ColumnValueResolverImpl<>(retriever, definer);
		return valueResolver;
	}
}
