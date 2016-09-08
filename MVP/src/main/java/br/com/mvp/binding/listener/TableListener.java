package br.com.mvp.binding.listener;

import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.ListConverter;

public class TableListener extends Listener<ListConverter<Object, Object>> implements TableModelListener {

	public TableListener(Object modelInstance, JPanel viewInstance, FieldMatch fieldMatch, ListConverter<Object, Object> converter) {
		super(modelInstance, viewInstance, fieldMatch, converter);
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		
	}

}
