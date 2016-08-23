package br.com.mvp.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import br.com.mvp.ComponentScanner;
import br.com.mvp.Instrumentator;
import javassist.ClassPool;
import javassist.CtClass;

public class ViewInstrumentator<V extends JPanel> implements Instrumentator<V>{

	private ClassPool cp = ClassPool.getDefault();
	private Class<V> viewClass;
	private CtClass ctViewClass;
	private Class<V> rewritenViewClass;
	private ViewComponentScanner viewScanner;
	
	private static Map<Class<?>, ViewInstrumentator<?>> instrumentatorCacheMap = new HashMap<>();
	
	public ViewInstrumentator(ClassPool cp, Class<V> viewClass, ComponentScanner modelScanner) throws Exception {
		super();
		this.cp = cp;
		this.viewClass = viewClass;
		this.viewScanner = new ViewComponentScanner(modelScanner, viewClass);
	}

	@Override
	public V get() throws Exception {
		return null;
	}

	@Override
	public ComponentScanner getScanner() {
		return null;
	}
}
