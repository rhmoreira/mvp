package br.com.mvp.view.dnd.list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JList;
import javax.swing.JList.DropLocation;
import javax.swing.TransferHandler.TransferSupport;

import br.com.mvp.util.JListUtil;
import br.com.mvp.view.dnd.DropDestination;
import br.com.mvp.view.dnd.TransferData;

class JListDropDestination<T> implements DropDestination<JList<T>> {

	private static final long serialVersionUID = 2559633666990432544L;

	@Override
	public void dropData(JList<T> jList, TransferSupport support, TransferData[] data) {
		JList.DropLocation dropLocation = (DropLocation) support.getDropLocation();
		int index = dropLocation.getIndex();
		
		JListUtil<T> jUtil = new JListUtil<>(jList);
		
		List<T> elements = Arrays
			.stream(data)
			.map(transfer -> (T) transfer.getData())
			.collect(Collectors.toList());
		
		if (!dropLocation.isInsert())
			jUtil.setValues(index, elements);
		else
			jUtil.addValues(index, elements);
	}

}
