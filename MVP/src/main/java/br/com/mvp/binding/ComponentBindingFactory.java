package br.com.mvp.binding;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.apache.commons.beanutils.ConstructorUtils;

import br.com.mvp.binding.impl.CheckboxBinding;
import br.com.mvp.binding.impl.ComboBinding;
import br.com.mvp.binding.impl.ComponentBinding;
import br.com.mvp.binding.impl.ListBinding;
import br.com.mvp.binding.impl.RadioBinding;
import br.com.mvp.binding.impl.TableBinding;
import br.com.mvp.binding.impl.TextBinding;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.annotation.Checkbox;
import br.com.mvp.view.annotation.Combo;
import br.com.mvp.view.annotation.Radio;
import br.com.mvp.view.annotation.Text;
import br.com.mvp.view.annotation.ViewList;
import br.com.mvp.view.annotation.ViewTable;

public final class ComponentBindingFactory {

	public static Map<Class<? extends Annotation>, Class<? extends Binding>> annotationBindingMap = new HashMap<>();
	
	static{
		annotationBindingMap.put(Checkbox.class, CheckboxBinding.class);
		annotationBindingMap.put(Combo.class, ComboBinding.class);
		annotationBindingMap.put(ViewList.class, ListBinding.class);
		annotationBindingMap.put(Radio.class, RadioBinding.class);
		annotationBindingMap.put(Text.class, TextBinding.class);
		annotationBindingMap.put(ViewTable.class, TableBinding.class);
	}
	
	private ComponentBindingFactory() {}
	
	public static Binding createBinding(Object model, JPanel view, FieldMatch match) throws Exception{
		Class<? extends Binding> bindingClass = 
				annotationBindingMap.get(match.getModelAnnotation().annotationType());
		
		ComponentBinding<?,?> binding = (ComponentBinding<?,?>) ConstructorUtils
				.invokeConstructor(bindingClass, new Object[]{model, view, match});
		binding.finallyBind();
		return binding;
	}
}
