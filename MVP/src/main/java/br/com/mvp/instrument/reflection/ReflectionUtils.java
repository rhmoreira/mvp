package br.com.mvp.instrument.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import br.com.mvp.binding.Bind;
import br.com.mvp.instrument.reflection.MemberHandler.MethodHandler;
import br.com.mvp.util.MVPUtil;

public class ReflectionUtils {

	public static void setFieldValue(Object target, Object value, Field field) throws Exception{
		setAccessible(field);
		
		field.set(target, value);
	}
	
	public static <T> T getFieldValue(Object target, Field field) throws Exception{
		setAccessible(field);
		
		return (T) field.get(target);
	}
	
	private static void setAccessible(Field field){
		if (!field.isAccessible())
			field.setAccessible(true);
	}
	
	public static Object invokeMethod(Object target, Method method, Object... value) throws Exception{
		return method.invoke(target, value);
	}
	
	public static <T extends Annotation> Annotation getStereotypedAnnotation(Class<T> stereotype, Field field){
		for (Annotation a: field.getAnnotations())
			if (a.annotationType().isAnnotationPresent(stereotype))
				return a;
		
		return null;
	}
	
	public static void copyProperties(Object src, Object dest) throws Exception{
		ClassHandler srcCH = createHandler(src);
		ClassHandler destCH = createHandler(dest);
		
		Map<String, Field> mappedDestFields = destCH.getMappedFields();
		srcCH.getScannedFields()
			 .stream()
			 .filter(f -> mappedDestFields.containsKey(f.getName()))
			 .forEach(f -> {
				 try{
					 Field destField = mappedDestFields.get(f.getName());
					 
					 MethodHandler srcGetter = srcCH.getMemberHandler().getterMethodForField(f);
					 MethodHandler destSetter = destCH.getMemberHandler().setterMethodForField(destField);
					 
					 destSetter.invoke(dest, srcGetter.invoke(src));
				 }catch (Exception e) {
					throw new RuntimeException(e);
				}
			 });
	}
	
	private static ClassHandler createHandler(Object obj) throws Exception{
		if (MVPUtil.isProxiedClass(obj.getClass()))
			return ((Bind)obj).getClassHandler(); 
		else
			return new ClassHandler(obj.getClass(), Object.class).scan();

	}
}