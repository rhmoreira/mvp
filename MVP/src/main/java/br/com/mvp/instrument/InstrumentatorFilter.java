package br.com.mvp.instrument;

import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

public interface InstrumentatorFilter {

	boolean accept(CtClass ctClass) throws Exception;
	boolean accept(CtField ctField) throws Exception;
	boolean accept(CtMethod ctMethod) throws Exception;
}
