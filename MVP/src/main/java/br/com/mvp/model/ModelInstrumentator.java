package br.com.mvp.model;

import br.com.mvp.ComponentScanner;
import br.com.mvp.Instrumentator;
import br.com.mvp.Util;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.bytecode.Descriptor;

@SuppressWarnings("unchecked")
public class ModelInstrumentator<M> implements Instrumentator<M> {

	private ClassPool cp;
	private Class<M> modelClass;
	private CtClass ctModelClass;
	private Class<M> rewritenModelClass;
	private ModelComponentScanner scanner;
	
	public ModelInstrumentator(ClassPool cp, Class<M> modelClass) throws Exception{
		super();
		this.cp = cp;
		this.modelClass = modelClass;
		this.ctModelClass = cp.getCtClass(modelClass.getName());
		this.scanner = new ModelComponentScanner(this.ctModelClass, modelClass);
	}
	
	private M getPropertyAwareModel() throws Exception{
		if (rewritenModelClass != null)
			return rewritenModelClass.newInstance();
		else{
			return createPropertyAwareModel();
		}
	}
	
	private M createPropertyAwareModel() throws Exception{
		CtClass ctModelAwareClass = cp.getCtClass(ModelPropertyAware.class.getName());
		ctModelAwareClass.setName(modelClass.getName() + "_$Javassist_PropertyAware");
		ctModelAwareClass.setSuperclass(ctModelClass);
		
		instrumentMethods(ctModelAwareClass);
		
		ctModelAwareClass.defrost();
		rewritenModelClass = ctModelAwareClass.toClass();
		return rewritenModelClass.newInstance();
	}
	
	private void instrumentMethods(CtClass ctModelAwareClass) throws Exception{
		for (CtField ctF: scanner.getScannedCtFields()){
			CtMethod newMethod = createOverwriteMethod(ctF, ctModelClass, ctModelAwareClass);
			ctModelAwareClass.addMethod(newMethod);
		}
		
	}
	
	private static CtMethod createOverwriteMethod(CtField ctF, CtClass ctClass, CtClass propertyAwareClass) throws Exception{
		String setterMethodName = "set" + Util.capitalize(ctF.getName());
		CtMethod declaredMethod = ctClass.getMethod(setterMethodName, Descriptor.ofMethod(CtClass.voidType, new CtClass[]{ctF.getType()}));
		CtMethod setterOverwrite = CtNewMethod.make(
				declaredMethod.getReturnType(), 
				declaredMethod.getName(),
				declaredMethod.getParameterTypes(),
				declaredMethod.getExceptionTypes(),
				"{ " +
					"super." + setterMethodName + "($$);" +
					"firePropertyChanged(\"" + ctF.getName()  + "\", $args[0]);" +	
				"}",
				propertyAwareClass);
		
		return setterOverwrite;
	}
	
	public ComponentScanner getScanner(){
		return scanner;
	}

	public M get() throws Exception{
		return getPropertyAwareModel();
	}
}
