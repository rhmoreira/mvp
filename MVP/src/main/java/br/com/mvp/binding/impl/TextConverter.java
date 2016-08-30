package br.com.mvp.binding.impl;

import java.math.BigDecimal;

import br.com.mvp.util.MVPUtil;
import br.com.mvp.view.ConverterType;

public class TextConverter {

	private ConverterType type;
	private int decimalDigits;
	private String value;
	
	
	public TextConverter(ConverterType type, int decimalDigits, String value) {
		super();
		this.type = type;
		this.decimalDigits = decimalDigits;
		this.value = value;
	}


	public Object convert(){
		if (type != ConverterType.NONE){
			BigDecimal number = new BigDecimal(value);
			if (decimalDigits > 0){
				number = number.setScale(decimalDigits, BigDecimal.ROUND_HALF_UP);
				number = MVPUtil.commaLeft(number, decimalDigits);
			}
			return type.getWrapper(number);
		}
		return value;
	}
}
