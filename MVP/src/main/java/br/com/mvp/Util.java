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
		return "set" + capitalize(str);
	}
}
