package br.com.mvp.model;

import br.com.mvp.instrument.ClassInstrumentator;
import javassist.CtClass;

public class ModelClassInstrumentator extends ClassInstrumentator{

	public ModelClassInstrumentator(CtClass ctClass, Class<?> topLevelClass) throws Exception {
		super(ctClass, topLevelClass, new ModelInstrumentatorFilter());
	}
}
