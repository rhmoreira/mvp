package br.com.mvp.instrument;

import br.com.mvp.instrument.reflection.ClassHandler;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

@SuppressWarnings("unchecked")
public abstract class AbstractProxyInstrumentator<T> implements Instrumentator<T>, InterfaceInstrumentator<T>, MethodInstrumentator<T> {

	private static final ProxyFactory proxy = new ProxyFactory();
	static{
		proxy.setUseCache(true);
	}
		
	protected Class<T> clazz;
	private ClassHandler classHandler;
	private boolean setup;
	
	public AbstractProxyInstrumentator(Class<T> clazz) throws Exception{
		this(clazz, 
				new ClassHandler(clazz, Object.class) {}
			);
	}
	
	public AbstractProxyInstrumentator(Class<T> clazz, ClassHandler classHandler) throws Exception{
		super();
		this.clazz = clazz;
		this.classHandler = classHandler;
		
		mapClass();
		
		InstrumentatorCache.write(clazz, this);
	}
	
	private void mapClass() throws Exception{
		classHandler.scan();
	}
	
	protected InterfaceInstrumentator<T> createProxy(){
		proxy.setSuperclass(clazz);
		return this;
	}
	
	@Override
	public MethodInstrumentator<T> setInterfaces(Class<?>... interfaces) {
		proxy.setInterfaces(interfaces);
		return this;
	}
	
	@Override
	public MethodInstrumentator<T> noExtraInterfaces() {
		return this;
	}

	@Override
	public Instrumentator<T> setMethodFilter(MethodFilter mFilter) {
		proxy.setFilter(mFilter);
		return this;
	}
	
	@Override
	public Instrumentator<T> useDefaultFilter() {
		return setMethodFilter(new DefaultMethodFilter());
	}

	@Override
	public T newInstance(MethodHandler mHandler) throws Exception {
		Class<T> proxyClass = (Class<T>) proxy.createClass();
		T newInstance = proxyClass.newInstance();
		((ProxyObject)newInstance).setHandler(mHandler);
		
		setup = true;
		return newInstance;
	}
	
	public ClassHandler getClassHandler() {
		return classHandler;
	}
	
	protected InstrumentatorCache getCache(){
		return InstrumentatorCache.getInstance();
	}

	protected boolean isSetup() {
		return setup;
	}
}
