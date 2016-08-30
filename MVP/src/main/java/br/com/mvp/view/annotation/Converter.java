package br.com.mvp.view.annotation;

import br.com.mvp.view.ConverterType;

public @interface Converter {

	ConverterType type() default ConverterType.NONE;
	
	int decimalDigits() default 0;
}
