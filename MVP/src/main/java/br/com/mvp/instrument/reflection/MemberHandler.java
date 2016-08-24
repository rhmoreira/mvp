package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.mvp.Util;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class MemberHandler {

	private Map<String, Field> fieldMap = new HashMap<>();
	private Map<String, Method> methodMap = new HashMap<>();
	
	private ClassMemberFilter filter;
	
	public MemberHandler(ClassMemberFilter filter) {
		super();
		this.filter = filter;
	}

	protected void mapFields(Class<?> clazz) throws Exception{
		Field[] declaredCtFields = clazz.getDeclaredFields();
		for (int i = 0; i<declaredCtFields.length; i++){
			if (filter.accept(declaredCtFields[i])){
				fieldMap.put(declaredCtFields[i].getName(), declaredCtFields[i]);
			}
		}
	}
	
	protected void mapGetterAndSetterMethods(Class<?> clazz) throws Exception{
		for (Field field: fieldMap.values()){
			String setterMethod = Util.generateSetterMethodName(field.getName());
			mapMethod(clazz, setterMethod, new Class[]{field.getType()}, field.getName());
			
			String getterMethod = Util.generateGetterMethodName(field.getName());
			mapMethod(clazz, getterMethod, new Class[]{}, field.getName());
		}
	}
	
	private void mapMethod(Class<?> clazz, String methodName, Class<?>[] parameter, String fieldName) throws Exception{
		Method declaredMethod = clazz.getDeclaredMethod(methodName, parameter);			
		if (filter.accept(declaredMethod))
			methodMap.put(fieldName + ":" + methodName, declaredMethod);
	}

	public Collection<Field> getScannedFields() {
		return fieldMap.values();
	}
	
	public Collection<Method> getScannedMethods() {
		return methodMap.values();
	}
}
