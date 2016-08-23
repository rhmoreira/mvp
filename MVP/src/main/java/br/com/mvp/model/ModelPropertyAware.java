package br.com.mvp.model;
import java.util.HashMap;
import java.util.Map;

import br.com.mvp.view.ComponentBind;
import br.com.mvp.view.annotation.Model;

@Model
public class ModelPropertyAware {

	private Map<String, ComponentBind> componentBindingMap = new HashMap<>();

	public void firePropertyChanged(String propertyName, Object value){
		System.out.println(propertyName + " " + value);
	}
}
