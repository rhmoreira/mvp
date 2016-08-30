package br.com.mvp.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class JListUtil<V> {
	
	private JList<V> jList;
	private DefaultListModel<V> listModel;
	
	public JListUtil(JList<V> jList) {
		this.jList = jList;
		listModel = (DefaultListModel<V>) jList.getModel();
	}

	public void addValues(Set<V> values){
		Set<V> currentValues = getValues();;
		listModel.removeAllElements();
		currentValues.addAll(values);
		
		for (V v: currentValues)
			listModel.addElement(v);
		
	}
	
	public void addValues(V[] values){
		addValues(new LinkedHashSet<>(Arrays.asList(values)));
	}
	
	public void removeValues(Set<V> values){
		for (V v: values)
			listModel.removeElement(v);
		
	}
	
	public Set<V> getValues(){
		int size = listModel.getSize();
		Set<V> linkedSet = new LinkedHashSet<>();
		for (int i = 0; i < size; i++)
			linkedSet.add(listModel.getElementAt(i));
		
		return linkedSet;
	}
	
	public Set<V> getSelectedElements(){
		List<V> selectedValuesList = jList.getSelectedValuesList();
		if (selectedValuesList == null || selectedValuesList.isEmpty())
			return Collections.emptySet();
		else
			return new LinkedHashSet<>(selectedValuesList);
	}
}
