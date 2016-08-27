package br.com.mvp.instrument;

import javax.swing.JPanel;

import br.com.mvp.model.ModelProxyInstrumentator;
import br.com.mvp.view.ViewProxyInstrumentator;

public final class InstrumentatorFactory {

	private InstrumentatorFactory() {}
	
	public static <T> Instrumentator<T> createModel(Class<T> clazz) throws Exception{
		Instrumentator<T> instrumentator = InstrumentatorCache.read(clazz);
		if (instrumentator == null)
			instrumentator = new ModelProxyInstrumentator<T>(clazz);
		
		return instrumentator;
	}
	
	public static <T extends JPanel> Instrumentator<T> createView(Class<T> clazz) throws Exception{
		Instrumentator<T> instrumentator = InstrumentatorCache.read(clazz);
		if (instrumentator == null)
			instrumentator = new ViewProxyInstrumentator<T>(clazz);
		
		return instrumentator;
	}
}