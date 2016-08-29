package br.com.mvp.binding.listener;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;

public class ListListener extends Listener implements ListSelectionListener, ListDataListener {

	public ListListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch) {
		super(modelInstance, viewInstance, fieldMatch);
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		try{
			if (!event.getValueIsAdjusting()){
				JList<?> jList = (JList<?>) event.getSource();
				java.util.List<?> values = jList.isSelectionEmpty() ? Collections.emptyList() : jList.getSelectedValuesList();
				updateModel(values);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		contentsChanged(e);
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		contentsChanged(e);
	}

	@Override
	public void contentsChanged(ListDataEvent event) {
		try{
			DefaultListModel<?> listModel = (DefaultListModel<?>) event.getSource();
			
			java.util.List<Object> values = new ArrayList<>();
			for (int i = 0; i < listModel.size(); i++)
				values.add(listModel.get(i));
			
			updateModel(values);
		
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
