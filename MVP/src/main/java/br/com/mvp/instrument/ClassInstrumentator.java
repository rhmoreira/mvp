package br.com.mvp.instrument;

import java.util.Collection;
import java.util.Collections;

import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

public class ClassInstrumentator {

	public static final String INSTRUMENTED_SUFFIX = "_$$_Javassist";
	
	private CtClass ctClass;
	private CtClass topLevelCtClass;
	private MemberInstrumentator memberInst;
	
	private InstrumentatorFilter filter;
	
	public ClassInstrumentator(CtClass ctClass, Class<?> topLevelClass) throws Exception{
		this(ctClass, topLevelClass, null);
	}
	
	public ClassInstrumentator(CtClass ctClass, Class<?> topLevelClass, InstrumentatorFilter filter) throws Exception{
		this(ctClass, topLevelClass, filter, null);
	}
	
	public ClassInstrumentator(CtClass ctClass, Class<?> topLevelClass, InstrumentatorFilter filter, MemberInstrumentator memberInst) throws Exception{
		super();
		this.ctClass = ctClass;
		this.topLevelCtClass = Instrumentator.classPool.get(topLevelClass.getName());
		this.filter = filter;
		this.memberInst = 
				(memberInst != null ? 
						memberInst :
						new MemberInstrumentator(filter == null ? new SimpleFilter() : filter)
				);
	}

	public Collection<CtField> getScannedFields() {
		return Collections.unmodifiableCollection(memberInst.getScannedFields());
	}
	
	public Collection<CtMethod> getScannedMethods() {
		return Collections.unmodifiableCollection(memberInst.getScannedMethods());
	}
	
	public void replaceFieldType(CtField field, CtClass newType) throws Exception{
		memberInst.replaceFieldType(field, newType);
	}
	
	public void scan() throws Exception{
		if (filter.accept(ctClass))
			mapClass(ctClass);
	}
	
	private void mapClass(CtClass ctClazz) throws Exception {
		if (!ctClazz.equals(topLevelCtClass)){
			mapClass(ctClazz.getSuperclass());

			memberInst.mapFields(ctClazz);
			memberInst.mapSetterMethods(ctClazz);
		}
	}
	
	public MemberInstrumentator getMemberInst() {
		return memberInst;
	}
	
	public CtClass defineSubClass(Class<?> clazz) throws Exception {
		CtClass ctSubClass = Instrumentator.classPool.get(clazz.getName());
		ctSubClass.setName(ctClass.getName() + INSTRUMENTED_SUFFIX);
		ctSubClass.defrost();
		ctSubClass.setSuperclass(ctClass);
		return ctSubClass;
	}
	
	public void overwriteMethod(CtMethod oroginalMethod, CtClass destSubClass, String body) throws Exception{
		memberInst.overwriteMethod(oroginalMethod, destSubClass, body);
	}
	
	private class SimpleFilter implements InstrumentatorFilter{

		public boolean accept(CtClass ctClass) throws Exception {
			return true;
		}
		public boolean accept(CtField ctField) throws Exception {
			return true;
		}
		public boolean accept(CtMethod ctMethod) throws Exception {
			return true;
		}
	}
}
