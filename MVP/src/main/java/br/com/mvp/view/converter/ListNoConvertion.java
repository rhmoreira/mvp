package br.com.mvp.view.converter;

import java.util.Collection;

public class ListNoConvertion implements ListConverter<Collection<Object>, Object> {

	@Override
	public Collection<Object> fromView(Collection<Object> value) {
		return value;
	}

	@Override
	public Collection<Object> fromModel(Collection<Object> value) {
		return value;
	}

}
