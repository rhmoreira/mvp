package br.com.mvp.instrument;

import java.util.Collection;

import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

@SuppressWarnings("unchecked")
public abstract class AbstractInstrumentator<T> implements Instrumentator<T> {

	private CtClass instrumentedClass;
	private Class<T> rewritenClass;
	private ClassInstrumentator classInstrumentator;
	
	public AbstractInstrumentator(CtClass ctClass) throws Exception{
		this(ctClass, 
				new ClassInstrumentator(ctClass, Object.class) {}
			);
	}
	
	public AbstractInstrumentator(CtClass ctClazz, ClassInstrumentator classInstrumentator) throws Exception{
		super();
		this.classInstrumentator = classInstrumentator;
		
		classInstrumentator.scan();
	}
	
	public final void instrument() throws Exception{
		instrumentedClass = getInstrumentedClass();
	}
	
	public T getInstrumentedInstance() throws Exception {
		if (rewritenClass != null)
			return rewritenClass.newInstance();
		else{
			instrumentedClass = getInstrumentedClass();
			rewritenClass = instrumentedClass.toClass();
			return rewritenClass.newInstance();
		}
	}
	
	protected abstract CtClass getInstrumentedClass() throws Exception;
	
	protected CtClass subclass(Class<?> clazz) throws Exception{
		return classInstrumentator.defineSubClass(clazz);
	}
	
	protected Collection<CtField> getScannedFields() {
		return classInstrumentator.getScannedFields();
	}
	
	protected Collection<CtMethod> getScannedMethods() {
		return classInstrumentator.getScannedMethods();
	}
	
	protected void replaceFieldType(CtField field, CtClass newType) throws Exception{
		classInstrumentator.replaceFieldType(field, newType);
	}
	
	public void overwriteMethod(CtMethod oroginalMethod, CtClass destSubClass, String body) throws Exception{
		classInstrumentator.overwriteMethod(oroginalMethod, destSubClass, body);
	}
	
}

