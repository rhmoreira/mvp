package br.com.mvp.view.converter;

/**
 * Converter for non-collection view elements
 * @author Renato
 *
 * @param <ModelValue>
 * @param <ViewValue>
 */
public interface Converter<ModelValue, ViewValue> {

	ModelValue fromView(ViewValue value);
	ViewValue fromModel(ModelValue value);
}
