package br.com.mvp.view.dnd.list;

import java.util.Set;

import javax.swing.JList;

import br.com.mvp.util.JListUtil;
import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.TransferData;

class JListDragSource<T> implements DragSource<JList<T>> {

	private static final long serialVersionUID = -4132321973108470887L;

	@Override
	public TransferData[] createTransferData(JList<T> jList) {
		JListUtil<T> jUtil = new JListUtil<>(jList);
		
		Set<T> selectedElements = jUtil.getSelectedElements();
		if(selectedElements.isEmpty())
			return null;
		else
			return selectedElements
					.stream()
					.map(o -> new ListTransferData(o))
					.toArray(size -> new TransferData[size]);
	}

}
