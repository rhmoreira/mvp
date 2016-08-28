package br.com.mvp.binding;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.apache.commons.beanutils.ConstructorUtils;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.annotation.Checkbox;
import br.com.mvp.view.annotation.Combo;
import br.com.mvp.view.annotation.List;
import br.com.mvp.view.annotation.Numeric;
import br.com.mvp.view.annotation.Radio;
import br.com.mvp.view.annotation.Text;

public final class ComponentBindingFactory {

	public static Map<Class<? extends Annotation>, Class<? extends Binding>> annotationBindingMap = new HashMap<>();
	
	static{
		annotationBindingMap.put(Checkbox.class, CheckboxComponentBinding.class);
		annotationBindingMap.put(Combo.class, ComboComponentBinding.class);
		annotationBindingMap.put(List.class, ListComponentBinding.class);
		annotationBindingMap.put(Numeric.class, NumericComponentBinding.class);
		annotationBindingMap.put(Radio.class, RadioComponentBinding.class);
		annotationBindingMap.put(Text.class, TextComponentBinding.class);
	}
	
	private ComponentBindingFactory() {}
	
	public static Binding createBinding(Object model, JPanel view, FieldMatch match) throws Exception{
		Class<? extends Binding> bindingClass = 
				annotationBindingMap.get(match.getModelAnnotation().annotationType());
		
		Binding binding = (Binding) ConstructorUtils
				.invokeConstructor(bindingClass, new Object[]{model, view, match});
		
		return binding;
	}
}
