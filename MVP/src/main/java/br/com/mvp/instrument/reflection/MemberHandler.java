package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.mvp.util.MVPUtil;

public class MemberHandler {

	private Map<String, Field> fieldMap = new HashMap<>();
	private Map<String, Method> methodMap = new HashMap<>();
	private boolean mapMethods = true;
	
	protected ClassMemberFilter filter;
	
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
		if (mapMethods){
			for (Field field: fieldMap.values()){
				Class<?> declaringClass = field.getDeclaringClass();
				
				String setterMethod = MVPUtil.generateSetterMethodName(field.getName());
				mapMethod(declaringClass, setterMethod, new Class[]{field.getType()}, field.getName());
				
				String getterMethod = generateGetterMethodName(field);
				mapMethod(declaringClass, getterMethod, new Class[]{}, field.getName());
			}
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
	
	private String generateGetterMethodName(Field field) {
		String getterMethod = 
				field.getType().isPrimitive() && field.getType() == boolean.class ?
					MVPUtil.generatePrimitiveBooleanGetterMethodName(field.getName()):
					MVPUtil.generateGetterMethodName(field.getName());
		return getterMethod;
	}

	public boolean isMethodMapped(Method method) {
		Method mappedMethod = getMappedMethods().get(method.getName());
		if (mappedMethod != null && mappedMethod.equals(method))
			return true;
		else
			return false;
	}

	public void setMapMethods(boolean mapMethods) {
		this.mapMethods = mapMethods;
	}
	
	public MethodHandler setterMethodForField(Field field){
		String setterMethodName = MVPUtil.generateSetterMethodName(field.getName());
		return new MethodHandler(methodMap.get(setterMethodName));
	}
	
	public MethodHandler getterMethodForField(Field field){
		String getterMethodName = generateGetterMethodName(field);
		return new MethodHandler(methodMap.get(getterMethodName));
	}
	
	public class MethodHandler{
		
		private Method method;

		public MethodHandler(Method method) {
			super();
			this.method = method;
		}
		
		public Object invoke(Object target, Object... args) throws Exception{
			return method.invoke(target, args);
		}
		
	}
}
