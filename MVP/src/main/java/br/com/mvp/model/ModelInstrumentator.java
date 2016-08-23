package br.com.mvp.model;

import br.com.mvp.Util;
import br.com.mvp.instrument.AbstractInstrumentator;
import br.com.mvp.instrument.ClassInstrumentator;
import br.com.mvp.view.annotation.Model;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

public class ModelInstrumentator<M> extends AbstractInstrumentator<M>{

	public ModelInstrumentator(CtClass modelClass) throws Exception{
		super(modelClass, new ModelClassInstrumentator(modelClass, Object.class));
	}

	@Override
	public CtClass getInstrumentedClass() throws Exception {
		CtClass subClass = subclass(ModelPropertyAware.class);
		
		replaceCompositComponentFields();
		overwriteSetters(subClass);
		
		return subClass;
	}
	
	@Override
	public M getInstrumentedInstance() throws Exception {
		return super.getInstrumentedInstance();
	}
	
	private void replaceCompositComponentFields() throws Exception{
		for (CtField ctf: getScannedFields()){
			if ( ctf.getAnnotation(Model.class) != null 
					&& !ctf.getName().contains(ClassInstrumentator.INSTRUMENTED_SUFFIX) ){
				
				Class<?> dependencyClass = Class.forName(ctf.getType().getName());
				ModelInstrumentator<?> dependencyInst = new ModelInstrumentator<>(classPool.get(dependencyClass.getName()));
				
				CtClass instrumentedDependency = dependencyInst.getInstrumentedClass();
				replaceFieldType(ctf, instrumentedDependency);
			}
		}
	}
	
	private void overwriteSetters(CtClass subClass) throws Exception{
		for (CtMethod method: getScannedMethods())
			if (method.getName().startsWith("set"))
				overwriteMethod(
						method, 
						subClass, 
						"{ " +
							"super." + method.getName() + "($$);" +
							"firePropertyChanged(\"" + Util.removeAndUncapitalize("set", method.getName())  + "\", $args[0]);" +	
						"}");
		
	}
}
