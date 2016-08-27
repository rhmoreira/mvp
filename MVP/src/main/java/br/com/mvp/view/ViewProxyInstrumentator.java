package br.com.mvp.view;

import javax.swing.JPanel;

import br.com.mvp.binding.Bind;
import br.com.mvp.binding.BindingInvocationHandler;
import br.com.mvp.instrument.AbstractProxyInstrumentator;
import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.ProxyInvocationHandler;
import br.com.mvp.instrument.reflection.ClassHandler;

public class ViewProxyInstrumentator<C extends JPanel> extends AbstractProxyInstrumentator<C> {

	public ViewProxyInstrumentator(Class<C> clazz) throws Exception {
		super(clazz, new ViewClassHandler<C>(clazz));
	}

	@Override
	public Instrumentator<C> setupProxy() {
		if (!isSetup()){
			return createProxy()
					.setInterfaces(Bind.class)
					.useDefaultFilter();
		}else
			return this;
	}

	@Override
	public C newInstance() throws Exception {
		ClassHandler classHandler = getClassHandler();
		
		ProxyInvocationHandler bindingInvocationHandler = 
				new BindingInvocationHandler(classHandler);
		
		C instance = super.newInstance(bindingInvocationHandler);
		
		
		return instance;
	}

}
