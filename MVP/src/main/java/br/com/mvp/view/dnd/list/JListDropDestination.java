package br.com.mvp.view.dnd.list;

import javax.swing.JList;
import javax.swing.JList.DropLocation;
import javax.swing.TransferHandler.TransferSupport;

import br.com.mvp.util.JListUtil;
import br.com.mvp.view.dnd.DraggingRule;
import br.com.mvp.view.dnd.DropDestination;
import br.com.mvp.view.dnd.TransferData;
import br.com.mvp.view.dnd.TransferSubDataExtractor;
import br.com.mvp.view.dnd.configuration.ElementFilter;

class JListDropDestination<T> implements DropDestination<JList<T>> {

	private static final long serialVersionUID = 2559633666990432544L;
	private DraggingRule rule;
	private JListConfiguration<T> conf;	
	
	public JListDropDestination(JListConfiguration<T> conf, DraggingRule rule) {
		this.conf = conf;
		this.rule = rule;
	}

	@Override
	public void dropData(JList<T> jList, TransferSupport support, TransferData[] data) {
		JList.DropLocation dropLocation = (DropLocation) support.getDropLocation();
		int index = dropLocation.getIndex();
		
		JListUtil<T> jUtil = new JListUtil<>(jList);
		
		ElementFilter<T> filter = conf.getDestFilter();
		
		for (TransferData transfer: data){
			T t = (T) transfer.getData();
			if (filter.accept(t)){
				if (!dropLocation.isInsert())
					jUtil.setValue(index++, t);
				else
					jUtil.addValue(index++, t);
			}
			
			for (TransferData subTransfer: TransferSubDataExtractor.extract(transfer))
				if (filter.accept((T) subTransfer.getData()))
					jUtil.addValue(index++, (T) subTransfer.getData());
		}
		
	}
	
	@Override
	public DraggingRule getRule() {
		return rule;
	}
}
