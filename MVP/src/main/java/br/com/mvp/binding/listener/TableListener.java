package br.com.mvp.binding.listener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import br.com.mvp.view.ViewModelBinder.ViewModelBind;
import br.com.mvp.view.ViewModelFieldMatcher.FieldMatch;
import br.com.mvp.view.converter.ListConverter;

public class TableListener extends Listener<ListConverter<Object, Object>> implements TableModelListener {

	public TableListener(ViewModelBind bind, FieldMatch fieldMatch, ListConverter<Object, Object> converter) {
		super(bind, fieldMatch, converter);
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		
	}

}
