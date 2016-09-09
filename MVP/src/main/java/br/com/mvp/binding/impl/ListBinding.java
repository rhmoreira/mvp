package br.com.mvp.binding.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import br.com.mvp.binding.listener.ListListener;
import br.com.mvp.util.JListUtil;
import br.com.mvp.view.ModelCollector;
import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.annotation.ViewList;
import br.com.mvp.view.converter.ListConverter;

public class ListBinding extends ComponentBinding<JList<Object>, ListConverter<Object, Object>> {

	private ListListener listener;
	
	public ListBinding(ViewModelBind bind, FieldMatch fieldMatch) throws Exception {
		super(bind, fieldMatch);
		this.listener = new ListListener(bind, fieldMatch, getConverter());
	}

	@Override
	protected void finallyBind(JList<Object> component) throws Exception {
		
		ViewList componentAnnotation = (ViewList) fieldMatch.getModelAnnotation();
		DefaultListModel<Object> listModel = (DefaultListModel<Object>) component.getModel();

		if (componentAnnotation.collectionType() == ModelCollector.SELECTED)
			component.addListSelectionListener(listener);
		else
			listModel.addListDataListener(listener);
	}
	
	@Override
	public void updateView() {
		try{
			Collection<Object> values = (Collection<Object>) getModelValue();
			DefaultListModel<?> listModel = (DefaultListModel<?>) getComponent().getModel();
			
			JListUtil<Object> jListUtil = new JListUtil<>(getComponent());
			jListUtil.addValues(new LinkedHashSet<>(values));
			
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
	public void updateModel() throws Exception {
		ViewList componentAnnotation = (ViewList) fieldMatch.getModelAnnotation();
		Collection<Object> values = new HashSet<>();
		if (componentAnnotation.collectionType() == ModelCollector.SELECTED)
			values.addAll(getComponent().getSelectedValuesList());
		else{
			JListUtil<Object> jListUtil = new JListUtil<>(getComponent());					
			values.addAll(jListUtil.getValues());
		}
		
		setModelValue(values);
	}

	@Override
	public void unbind() {
		getComponent().removeListSelectionListener(listener);
		getComponent().getModel().removeListDataListener(listener);
		super.unbind();
	}

	

}
