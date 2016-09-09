package br.com.mvp.binding.listener;

import java.util.Collections;

import javax.swing.JList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.mvp.reflection.ReflectionUtils;
import br.com.mvp.util.JListUtil;
import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.ListConverter;

public class ListListener extends Listener<ListConverter<Object, Object>> implements ListSelectionListener, ListDataListener {

	public ListListener(ViewModelBind bind, FieldMatch fieldMatch, ListConverter<Object, Object> converter) {
		super(bind, fieldMatch, converter);
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		try{
			if (!event.getValueIsAdjusting()){
				JList<Object> jList = (JList<Object>) event.getSource();
				java.util.List<Object> values = jList.isSelectionEmpty() ? Collections.emptyList() : jList.getSelectedValuesList();
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
			JList<Object> jList = ReflectionUtils.getFieldValue(getViewInstance(), fieldMatch.getViewField());
			JListUtil<Object> jListUtil = new JListUtil<>(jList);
			updateModel(jListUtil.getValues());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
