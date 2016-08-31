package br.com.mvp.view.converter;

import java.util.Collection;

/**
 * Default converter Class if no conversion needs to be done.
 * @author Renato
 *
 */
public class ListNoConversion implements ListConverter<Collection<Object>, Object> {

	@Override
	public Collection<Object> fromView(Collection<Object> value) {
		return value;
	}

	@Override
	public Collection<Object> fromModel(Collection<Object> value) {
		return value;
	}

}
