package br.com.mvp.view.converter;

/**
 * Default converter Class if no conversion needs to be done.
 * @author Renato
 */
public class SimpleNoConversion implements Converter<Object,Object>{

	@Override
	public Object fromView(Object value) {
		return value;
	}

	@Override
	public Object fromModel(Object value) {
		return value;
	}

	
}
