package br.com.mvp.view.converter;

public interface Converter<ModelValue, ViewValue> {

	ModelValue fromView(ViewValue value);
	ViewValue fromModel(ModelValue value);
}
