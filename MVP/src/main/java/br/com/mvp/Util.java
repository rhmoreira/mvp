package br.com.mvp;

public class Util {


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
	
	private static String appendPrefixAndCapitalize(String prefix, String str){
		return prefix + capitalize(str);
	}
}
