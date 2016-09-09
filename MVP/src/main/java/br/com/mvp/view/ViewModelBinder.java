package br.com.mvp.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import br.com.mvp.binding.Binding;
import br.com.mvp.binding.ComponentBindingFactory;
import br.com.mvp.reflection.ClassHandler;
import br.com.mvp.reflection.ReflectionUtils;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class ViewModelBinder {

	private Object modelInstance;
	private JPanel view;
	private ClassHandler viewClassHandler;
	private ClassHandler modelClassHandler;
	
	public ViewModelBinder(Object model, JPanel view) throws Exception{
		super();
		this.modelInstance = model;
		this.modelClassHandler = new ModelClassHandler(model.getClass()).scan();
		this.view = view;
		this.viewClassHandler = new ViewClassHandler<>(view.getClass()).scan();
	}

	public List<Binding> bind() throws Exception{
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
			ViewModelBinder vmb = new ViewModelBinder(modelInstance, innerPannel);
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
			Object model = ReflectionUtils.getFieldValue(modelInstance, f);
			ViewModelBinder vmb = new ViewModelBinder(model, view);
			bindings.addAll(vmb.bindModel());
		}
		
		ViewModelFieldMatcher matcher = new ViewModelFieldMatcher();
		List<FieldMatch> matchList = matcher.match(viewClassHandler.getScannedFields(), modelClassHandler.getScannedFields());
		
		for (FieldMatch match: matchList){
			ViewModelBind bind = new ViewModelBinder.ViewModelBind(modelInstance, view, viewClassHandler, modelClassHandler);
			bindings.add(
					ComponentBindingFactory.createBinding(bind, match));
		}
		return bindings;
	}
	
	public static class ViewModelBind{
		private Object model;
		private JPanel view;
		private ClassHandler viewClassHandler;
		private ClassHandler modelClassHandler;
		
		public ViewModelBind(Object modelInstance, JPanel view, ClassHandler viewClassHandler,
				ClassHandler modelClassHandler) {
			super();
			this.model = modelInstance;
			this.view = view;
			this.viewClassHandler = viewClassHandler;
			this.modelClassHandler = modelClassHandler;
		}

		public Object getModel() {
			return model;
		}
		public JPanel getView() {
			return view;
		}
		public ClassHandler getViewClassHandler() {
			return viewClassHandler;
		}
		public ClassHandler getModelClassHandler() {
			return modelClassHandler;
		}
		
		public void unbind(){
			this.model = null;
			this.view = null;
			this.viewClassHandler = null;
			this.modelClassHandler = null;
		}
	}
}

