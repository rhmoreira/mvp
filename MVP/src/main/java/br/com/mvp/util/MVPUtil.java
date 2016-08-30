package br.com.mvp.util;

import java.math.BigDecimal;

import javassist.util.proxy.ProxyFactory;

@SuppressWarnings("unchecked")
public class MVPUtil {


	public static String capitalize(String str){
		return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
	}
	
	public static String uncapitalize(String str){
		return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
	}
	
	public static String removeAndUncapitalize(String removableContent, String str){
		return uncapitalize(str.replace(removableContent, ""));
	}
	
	public static String generateSetterMethodName(String str){
		return appendPrefixAndCapitalize("set", str);
	}
	
	public static String generateGetterMethodName(String str){
		return appendPrefixAndCapitalize("get", str);
	}
	
	public static String generatePrimitiveBooleanGetterMethodName(String str){
		return appendPrefixAndCapitalize("is", str);
	}
	
	private static String appendPrefixAndCapitalize(String prefix, String str){
		return prefix + capitalize(str);
	}
	
	public static <T> Class<? super T> getProxiedClass(T instance){
		return getProxiedClass((Class<T>)instance.getClass());
	}
	
	public static <T> Class<? super T> getProxiedClass(Class<T> clazz){
		if (clazz == null)
			return clazz;
		
		if (isProxiedClass(clazz))
			return clazz.getSuperclass();
		else
			return clazz;
	}
	
	public static <T> boolean isProxiedClass(Class<T> clazz){
		if (clazz == null)
			return false;
		
		return ProxyFactory.isProxyClass(clazz);
	}
	
	public static String onlyDigits(String str){
		return str.replaceAll("[^\\d]", "");
	}
	
	public static BigDecimal commaLeft(BigDecimal number, int nTimes){
		if (nTimes > 0)
			for (int i=0; i < nTimes; i++)
				number = number.divide(BigDecimal.TEN);
		
		return number;
	}
	
}
