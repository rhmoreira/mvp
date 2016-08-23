package br.com.mvp;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ComponentScanner {
	
	private Map<String, Field> fieldMap = new HashMap<>();
	private Class<?> topLevelClass;
	
	public ComponentScanner(Class<?> clazz, Class<?> topLevelClass) throws Exception{
		super();
		this.topLevelClass = topLevelClass;
		mapFields(clazz);
	}

	public Collection<Field> getScannedFields() throws Exception{
		return Collections.unmodifiableCollection(fieldMap.values());
	}
	
	private void mapFields(Class<?> clazz) throws Exception {
		if (!clazz.equals(topLevelClass)){
			mapFields(clazz.getSuperclass());
			Field[] declaredFields = clazz.getDeclaredFields();
			for (int i = 0; i<declaredFields.length; i++){
				if (accept(declaredFields[i]))
					fieldMap.put(declaredFields[i].getName(), declaredFields[i]);
			}
		}
	}
	
	protected abstract boolean accept(Field f) throws Exception;
}
