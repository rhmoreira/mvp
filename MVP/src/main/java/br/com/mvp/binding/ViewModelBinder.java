package br.com.mvp.binding;

import java.util.ArrayList;
import java.util.List;

public class ViewModelBinder {

	private Bind modelBind;
	private Bind viewBind;
	
	public ViewModelBinder(Bind modelBind, Bind viewBind) {
		super();
		this.modelBind = modelBind;
		this.viewBind = viewBind;
	}

	public List<Binding> bind(){
		List<Binding> bindings = new ArrayList<>();
		
		return null;
	}
	
}

