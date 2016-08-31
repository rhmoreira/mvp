package br.com.mvp.view.converter;

public class SimpleNoConvertion implements Converter<Object,Object>{

	@Override
	public Object fromView(Object value) {
		return value;
	}

	@Override
	public Object fromModel(Object value) {
		return value;
	}

	
}
