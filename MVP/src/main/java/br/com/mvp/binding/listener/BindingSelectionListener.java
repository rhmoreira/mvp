package br.com.mvp.binding.listener;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.mvp.view.ModelCollector;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.annotation.List;

public class BindingSelectionListener extends BindingListener implements ListSelectionListener {

	public BindingSelectionListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) {
		super(modelInstance, viewInstance, fieldMatch);
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		try{
			JList<?> jList = (JList<?>) event.getSource();
			if (!event.getValueIsAdjusting())
				updateModel(getValues(jList));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private java.util.List<?> getValues(JList<?> jList){
		List listAnnotation = (List) fieldMatch.getModelAnnotation();
		DefaultListModel<?> listModel = listAnnotation.listModel().cast(jList.getModel());
		
		if (listAnnotation.collectionType() == ModelCollector.SELECTED)
			return jList.isSelectionEmpty() ? Collections.emptyList() : jList.getSelectedValuesList();
		else{
			java.util.List<Object> values = new ArrayList<>();
			for (int i = 0; i < listModel.size(); i++)
				values.add(listModel.get(i));
			return values;
		}
	}

}
