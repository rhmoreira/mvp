package br.com.mvp.view.table.mapper;

class ColumnValueResolverImpl<T> implements ColumnValueResolver<T>{

	private ValueRetriever<T> retriever;
	private ValueDefiner<T> definer;
	
	public ColumnValueResolverImpl(ValueRetriever<T> retriever, ValueDefiner<T> definer) {
		super();
		this.retriever = retriever;
		this.definer = definer;
	}

	@Override
	public Object getColumnValue(T target) {
		return retriever.retrieve(target);
	}

	@Override
	public void setColumnValue(T target, Object value) {
		definer.define(target, value);
	}

}
