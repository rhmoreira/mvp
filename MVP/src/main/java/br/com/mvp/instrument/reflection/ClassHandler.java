package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;

public class ClassHandler {

	private Class<?> clazz;
	private Class<?> topLevelClass;
	private MemberHandler memberHandler;
	
	private ClassMemberFilter filter;
	
	public ClassHandler(Class<?> clazz, Class<?> topLevelClass) throws Exception{
		this(clazz, topLevelClass, null);
	}
	
	public ClassHandler(Class<?> clazz, Class<?> topLevelClass, ClassMemberFilter filter) throws Exception{
		this(clazz, topLevelClass, filter, null);
	}
	
	public ClassHandler(Class<?> clazz, Class<?> topLevelClass, ClassMemberFilter filter, MemberHandler memberInst) throws Exception{
		super();
		this.clazz = clazz;
		this.topLevelClass = topLevelClass;
		this.filter = filter;
		this.memberHandler = 
				(memberInst != null ? 
						memberInst :
						new MemberHandler(filter == null ? new SimpleFilter() : filter)
				);
	}
	
	public Collection<Field> getScannedFields() {
		return Collections.unmodifiableCollection(memberHandler.getScannedFields());
	}
	
	public Collection<Method> getScannedMethods() {
		return Collections.unmodifiableCollection(memberHandler.getScannedMethods());
	}
	
	public void scan() throws Exception{
		if (filter.accept(clazz))
			mapClass(clazz);
	}
	
	private void mapClass(Class<?> clazz) throws Exception {
		if (!clazz.equals(topLevelClass)){
			mapClass(clazz.getSuperclass());

			memberHandler.mapFields(clazz);
			memberHandler.mapGetterAndSetterMethods(clazz);
		}
	}
	
	private class SimpleFilter implements ClassMemberFilter{

		public boolean accept(Class<?> clazz) throws Exception {
			return true;
		}
		public boolean accept(Field ctField) throws Exception {
			return true;
		}
		public boolean accept(Method ctMethod) throws Exception {
			return true;
		}
	}
}
