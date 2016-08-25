package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.mvp.Util;

public class MemberHandler {

	private Map<String, Field> fieldMap = new HashMap<>();
	private Map<String, Method> methodMap = new HashMap<>();
	
	private ClassMemberFilter filter;
	
	public MemberHandler(ClassMemberFilter filter) {
		super();
		this.filter = filter;
	}

	protected Field[] mapFields(Class<?> clazz) throws Exception{
		Field[] declaredCtFields = clazz.getDeclaredFields();
		Map<String, Field> filteredFields = 
			Arrays.stream(declaredCtFields)
				  .filter(f -> filter.accept(f))
				  .collect(Collectors.toMap((Function<Field,String>) f -> f.getName(), (Function<Field,Field>) f -> f));
		
		fieldMap.putAll(filteredFields);
		return declaredCtFields;
	}
	
	protected void mapGettersAndSetters(Field[] acceptedFields) throws Exception{
		for (Field field: fieldMap.values()){
			Class<?> declaringClass = field.getDeclaringClass();
			
			String setterMethod = Util.generateSetterMethodName(field.getName());
			mapMethod(declaringClass, setterMethod, new Class[]{field.getType()}, field.getName());
			
			String getterMethod = generateGetterMethodName(field);
			mapMethod(declaringClass, getterMethod, new Class[]{}, field.getName());
		}
	}

	private void mapMethod(Class<?> clazz, String methodName, Class<?>[] parameter, String fieldName) throws Exception{
		Method declaredMethod = clazz.getDeclaredMethod(methodName, parameter);			
		if (filter.accept(declaredMethod))
			methodMap.put(methodName, declaredMethod);
	}

	protected Collection<Field> getScannedFields() {
		return fieldMap.values();
	}
	
	protected Map<String, Field> getMappedFields() {
		return fieldMap;
	}
	
	protected Collection<Method> getScannedMethods() {
		return methodMap.values();
	}
	
	protected Map<String, Method> getMappedMethods() {
		return methodMap;
	}
	
	public Method setterMethodForField(Field field){
		String setterMethodName = Util.generateSetterMethodName(field.getName());
		Method setterMethod = methodMap.get(setterMethodName);
		return setterMethod;
	}
	
	public Method getterMethodForField(Field field){
		String getterMethodName = generateGetterMethodName(field);
		Method getterMethod = methodMap.get(getterMethodName);
		return getterMethod;
	}
	
	private String generateGetterMethodName(Field field) {
		String getterMethod = 
				field.getType().isPrimitive() && field.getType() == boolean.class ?
					Util.generatePrimitiveBooleanGetterMethodName(field.getName()):
					Util.generateGetterMethodName(field.getName());
		return getterMethod;
	}

	public boolean isMethodMapped(Method method) {
		Method mappedMethod = getMappedMethods().get(method.getName());
		if (mappedMethod != null && mappedMethod.equals(method))
			return true;
		else
			return false;
	}
}
