package br.com.mvp.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import br.com.mvp.binding.Bind;
import br.com.mvp.binding.Binding;
import br.com.mvp.binding.ComponentBindingFactory;
import br.com.mvp.instrument.reflection.ClassHandler;
import br.com.mvp.instrument.reflection.ReflectionUtils;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class ViewModelBinder {

	private Bind modelBind;
	private JPanel view;
	private ClassHandler viewClassHandler;
	private ClassHandler modelClassHandler;
	
	public ViewModelBinder(Bind modelBind, JPanel view) throws Exception{
		super();
		this.modelBind = modelBind;
		this.modelClassHandler = modelBind.getClassHandler();
		this.view = view;
		this.viewClassHandler = new ViewClassHandler<>(view.getClass());
	}

	public List<Binding> bind() throws Exception{
		viewClassHandler.scan();
		
		List<Binding> bindings = new ArrayList<>();
		
		bindings.addAll(bindView());
		bindings.addAll(bindModel());
		
		return bindings;
	}

	private List<Binding> bindView() throws Exception{
		List<Binding> bindings = new ArrayList<>();
		Set<Field> dependencyFields = viewClassHandler
				.getDependencyMapper()
				.getDependencies()
				.keySet();
		for (Field f: dependencyFields){
			JPanel innerPannel = ReflectionUtils.getFieldValue(view, f);
			ViewModelBinder vmb = new ViewModelBinder(modelBind, innerPannel);
			bindings.addAll(vmb.bind());
		}
		
		return bindings;
	}

	private Collection<? extends Binding> bindModel() throws Exception {
		List<Binding> bindings = new ArrayList<>();
		Set<Field> dependencyFields = modelClassHandler
				.getDependencyMapper()
				.getDependencies()
				.keySet();
		for (Field f: dependencyFields){
			Bind model = ReflectionUtils.getFieldValue(modelBind, f);
			ViewModelBinder vmb = new ViewModelBinder(model, view);
			bindings.addAll(vmb.bind());
		}
		
		System.out.println(modelBind);
		System.out.println(view);
		
		ViewModelFieldMatcher matcher = new ViewModelFieldMatcher();
		List<FieldMatch> matchList = matcher.match(viewClassHandler.getScannedFields(), modelClassHandler.getScannedFields());
		
		for (FieldMatch match: matchList)
			bindings.add(
					ComponentBindingFactory.createBinding(modelBind, view, match));
		return bindings;
	}
	
}

