package br.com.mvp.model;

import java.lang.annotation.Annotation;

import br.com.mvp.instrument.InstrumentatorFilter;
import br.com.mvp.view.annotation.Model;
import br.com.mvp.view.annotation.View;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

class ModelInstrumentatorFilter implements InstrumentatorFilter{

		@Override
		public boolean accept(CtClass ctClass) throws Exception {
			return ctClass.getAnnotation(Model.class) != null;
		}

		@Override
		public boolean accept(CtField ctField) throws Exception {
			Object[] annotations = ctField.getAnnotations();
			for (Object a: annotations)
				if (((Annotation)a).annotationType().isAnnotationPresent(View.class))
					return true;
			
			return accept(ctField.getType());
			
		}

		@Override
		public boolean accept(CtMethod ctMethod) throws Exception {
			return ctMethod.getName().startsWith("set")
					|| ctMethod.getName().startsWith("get");
		}		
	}