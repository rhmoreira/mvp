package br.com.mvp.view;

import java.math.BigDecimal;

public enum ConverterType {

	NONE		(null),
	LONG		( (number) -> number.longValue() ),
	INTEGER		( (number) -> number.intValue() ),
	SHORT		( (number) -> number.shortValue() ),
	DOUBLE		( (number) -> number.doubleValue() ),
	FLOAT		( (number) -> number.floatValue() ),
	BIG_DECIMAL	( (number) -> number ),
	;
	
	private BigDecimalToWrapper bdToW;
	
	private ConverterType(BigDecimalToWrapper bdToW) {
	}
	
	@FunctionalInterface
	private interface BigDecimalToWrapper{
		Object getValue(BigDecimal number);
	}
	
	public Object getWrapper(BigDecimal number){
		return bdToW.getValue(number);
	}
}
