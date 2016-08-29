package br.com.mvp.binding.impl;

import java.lang.reflect.Field;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

import org.apache.commons.beanutils.ConstructorUtils;

import br.com.mvp.binding.listener.ListListener;
import br.com.mvp.instrument.reflection.ReflectionUtils;
import br.com.mvp.view.ModelCollector;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.annotation.List;

public class ListBinding extends ComponentBinding<JList<Object>> {

	private ListListener listener;
	
	public ListBinding(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) throws Exception {
		super(modelInstance, viewInstance, fieldMatch);
		this.listener = new ListListener(modelInstance, viewInstance, fieldMatch);
	}

	@Override
	protected void finallyBind(JList<Object> component) throws Exception {
		
		List componentAnnotation = (List) fieldMatch.getModelAnnotation();
		
		DefaultListModel<? extends Object> listModel = ConstructorUtils
				.invokeConstructor(componentAnnotation.model(), new Object[]{});
		component.setModel((ListModel<Object>) listModel);

		if (componentAnnotation.collectionType() == ModelCollector.SELECTED)
			component.addListSelectionListener(listener);
		else
			listModel.addListDataListener(listener);
	}
	
	@Override
	public void updateView() {
		try{
			Field modelField = fieldMatch.getModelField();
			Collection<?> values = ReflectionUtils.getFieldValue(modelInstance, modelField);
			DefaultListModel<?> listModel = (DefaultListModel<?>) getComponent().getModel();
			
			int[] indexes = new int[values.size()];
			int counter = 0;
			for (Object value: values)
				indexes[counter++] = listModel.indexOf(value);
			
			getComponent().setSelectedIndices(indexes);			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateModel() {
	}

	@Override
	public void undoBinding() {
	}

	

}
