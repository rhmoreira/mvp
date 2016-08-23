	package br.com.mvp;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import br.com.mvp.model.ModelInstrumentator;
import br.com.mvp.view.ViewInstrumentator;
import javassist.ClassPool;

@SuppressWarnings({"rawtypes", "unchecked"})
final class InstrumentatorFactory {

	public static final ClassPool CP = ClassPool.getDefault();
	
	private static Map<Class<?>, Instrumentator<?>> instrumentatorCacheMap = new HashMap<>();
	
	
	private InstrumentatorFactory() {}
	
	public static <T> Instrumentator<T> create(Class<T> instrumentedClass) throws Exception{
		return create0(InstrumentatorType.MODEL, instrumentedClass, null);
	}
	
	public static <T extends JPanel> Instrumentator<T> create(Class<T> instrumentedClass, ComponentScanner scanner) throws Exception{
		return create0(InstrumentatorType.VIEW, instrumentedClass, scanner);
	}
	
	private static <T> Instrumentator<T> create0(InstrumentatorType type, Class<T> instrumentedClass, ComponentScanner scanner) throws Exception{
		Instrumentator<?> instrumentator = instrumentatorCacheMap.get(instrumentedClass);
		if (instrumentator == null){
			switch (type) {
			case MODEL:
				instrumentator = new ModelInstrumentator<T>(CP, instrumentedClass);
				break;
			case VIEW:
				instrumentator = new ViewInstrumentator(CP, instrumentedClass, scanner);
				break;
			}
			instrumentatorCacheMap.put(instrumentedClass, instrumentator);
		}
		return (Instrumentator<T>) instrumentator;
	}
}