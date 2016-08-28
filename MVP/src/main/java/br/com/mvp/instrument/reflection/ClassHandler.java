package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class ClassHandler {

	private Class<?> clazz;
	private Class<?> topLevelClass;
	private MemberHandler memberHandler;
	private Injector injector = new InjectorImpl();
	private DependencyMapperImpl dependencyMapper = new DependencyMapperImpl(this);
	
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
	
	public Map<String, Field> getMappedFields() {
		return Collections.unmodifiableMap(memberHandler.getMappedFields());
	}
	
	public Map<String, Method> getMappedMethods() {
		return Collections.unmodifiableMap(memberHandler.getMappedMethods());
	}
	
	public ClassHandler scan() throws Exception{
		if (filter.accept(clazz)){
			mapClass(clazz);
			dependencyMapper.mapModelDependencies();
		}
		
		return this;
	}
	
	private void mapClass(Class<?> clazz) throws Exception {
		if (!clazz.equals(topLevelClass)){
			mapClass(clazz.getSuperclass());

			Field[] fields = memberHandler.mapFields(clazz);
			memberHandler.mapGettersAndSetters(fields);
		}
	}
	
	public ClassMemberFilter getFilter(){
		return filter;
	}
	
	private class SimpleFilter implements ClassMemberFilter{

		public boolean accept(Class<?> clazz){
			return true;
		}
		public boolean accept(Field ctField){
			return true;
		}
		public boolean accept(Method ctMethod){
			return true;
		}
	}

	public Injector getInjector() {
		return injector;
	}
	
	public DependencyMapper getDependencyMapper() {
		return dependencyMapper;
	}
	
	public MemberHandler getMemberHandler() {
		return memberHandler;
	}
	
	public Class<?> getHandledClass(){
		return clazz;
	}
}
