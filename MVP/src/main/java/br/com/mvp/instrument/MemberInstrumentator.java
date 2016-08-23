package br.com.mvp.instrument;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.mvp.Util;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class MemberInstrumentator {

	private Map<String, CtField> ctFieldMap = new HashMap<>();
	private Map<String, CtMethod> ctMethodMap = new HashMap<>();
	
	private InstrumentatorFilter filter;
	
	public MemberInstrumentator(InstrumentatorFilter filter) {
		super();
		this.filter = filter;
	}

	protected void mapFields(CtClass ctClass) throws Exception{
		CtField[] declaredCtFields = ctClass.getDeclaredFields();
		for (int i = 0; i<declaredCtFields.length; i++){
			if (filter.accept(declaredCtFields[i])){
				ctFieldMap.put(declaredCtFields[i].getName(), declaredCtFields[i]);
			}
		}
	}
	
	protected void mapSetterMethods(CtClass ctClass) throws Exception{
		for (CtField ctField: ctFieldMap.values()){
			String setterMethod = Util.generateSetterMethodName(ctField.getName());
			CtMethod declaredMethod = ctClass.getDeclaredMethod(setterMethod, new CtClass[]{ctField.getType()});
			
			if (filter.accept(declaredMethod))
				ctMethodMap.put(ctField.getName() + ":" + setterMethod, declaredMethod);
		}
	}
	
	public void overwriteMethod(CtMethod oroginalMethod, CtClass destSubClass, String body) throws Exception{
		CtMethod overwritenMethod = CtNewMethod.make(
				oroginalMethod.getReturnType(), 
				oroginalMethod.getName(),
				oroginalMethod.getParameterTypes(),
				oroginalMethod.getExceptionTypes(),
				body,
				destSubClass);
		
		destSubClass.addMethod(overwritenMethod);
	}
	
	public Collection<CtField> getScannedFields() {
		return ctFieldMap.values();
	}
	
	public Collection<CtMethod> getScannedMethods() {
		return ctMethodMap.values();
	}
	
	public void replaceFieldType(CtField field, CtClass newType) throws Exception{
		field.setType(newType);
		ctFieldMap.put(field.getName(), field);
	}
}
