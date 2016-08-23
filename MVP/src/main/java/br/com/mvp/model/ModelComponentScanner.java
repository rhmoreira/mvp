package br.com.mvp.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.mvp.ComponentScanner;
import br.com.mvp.view.annotation.ViewComponent;
import javassist.CtClass;
import javassist.CtField;

public class ModelComponentScanner extends ComponentScanner {

	private Map<String, CtField> ctFieldMap = new HashMap<>();
	
	public ModelComponentScanner(CtClass ctModelClass, Class<?> clazz) throws Exception{
		super(clazz, Object.class);
	}
	
	public Collection<CtField> getScannedCtFields() throws Exception{
		return Collections.unmodifiableCollection(ctFieldMap.values());
	}
	
	@Override
	protected boolean accept(Field f) throws Exception{
		return isViewComponent(f);
	}
	
	private boolean isViewComponent(Field ctF) throws Exception{
		Annotation[] annotations = ctF.getAnnotations();
		for (Annotation annotation: annotations)
			if (annotation.annotationType().getAnnotation(ViewComponent.class) != null)
				return true;
		
		return false;
	}
}
