package br.com.mvp.binding;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.com.mvp.instrument.reflection.ClassHandler;

public class ViewModelBinder {

	private Bind modelBind;
	private JPanel view;
	
	public ViewModelBinder(Bind modelBind, JPanel view) {
		super();
		this.modelBind = modelBind;
		this.view = view;
	}

	public List<Binding> bind(){
		ClassHandler modelClassHandler = modelBind.getClassHandler();
		
		List<Binding> bindings = new ArrayList<>();
		
		
		return null;
	}
	
}

