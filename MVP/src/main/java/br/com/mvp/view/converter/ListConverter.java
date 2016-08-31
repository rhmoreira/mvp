package br.com.mvp.view.converter;

import java.util.Collection;

/**
 * Converter for Collection based view elements
 * @author Renato
 *
 * @param <ModelValue>
 * @param <ViewValue>
 */
public interface ListConverter<ModelValue, ViewValue> extends Converter<ModelValue, Collection<ViewValue>> {

}
