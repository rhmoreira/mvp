package br.com.mvp.view.dnd.list;

import javax.swing.JList;
import javax.swing.JList.DropLocation;
import javax.swing.TransferHandler.TransferSupport;

import br.com.mvp.util.JListUtil;
import br.com.mvp.view.dnd.DraggingRule;
import br.com.mvp.view.dnd.DropDestination;
import br.com.mvp.view.dnd.TransferData;
import br.com.mvp.view.dnd.TransferSubDataExtractor;

class JListDropDestination<T> implements DropDestination<JList<T>> {

	private static final long serialVersionUID = 2559633666990432544L;
	private DraggingRule rule;
	
	public JListDropDestination(DraggingRule rule) {
		this.rule = rule;
	}

	@Override
	public void dropData(JList<T> jList, TransferSupport support, TransferData[] data) {
		JList.DropLocation dropLocation = (DropLocation) support.getDropLocation();
		int index = dropLocation.getIndex();
		
		JListUtil<T> jUtil = new JListUtil<>(jList);
		
		for (TransferData transfer: data){
			if (!dropLocation.isInsert())
				jUtil.setValue(index++, (T) transfer.getData());
			else
				jUtil.addValue(index++, (T) transfer.getData());
			
			for (TransferData subTransfer: TransferSubDataExtractor.extract(transfer))
				jUtil.addValue(index++, (T) subTransfer.getData());
		}
		
	}
	
	@Override
	public DraggingRule getRule() {
		return rule;
	}
}
