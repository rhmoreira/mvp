package br.com.mvp.util;

import java.util.Arrays;
import java.util.Collection;
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

	public void addValues(int startIndex, Collection<V> values){
		for (V v: values){
			if (!listModel.contains(v)){
				listModel.add(startIndex++, v);
			}
		}
	}
	
	public void setValues(int startIndex, Collection<V> values){
		for (V v: values){
			if (!listModel.contains(v))
				if (listModel.size() == startIndex)
					listModel.setSize(listModel.size() + 1);
				listModel.set(startIndex++, v);
		}
	}
	
	public void addValues(Collection<V> values){
		addValues(listModel.size(), values);
	}
	
	public void addValues(V[] values){
		addValues(listModel.size(), values);
	}
	
	public void addValues(int startIndex, V[] values){
		addValues(startIndex, new LinkedHashSet<>(Arrays.asList(values)));
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
