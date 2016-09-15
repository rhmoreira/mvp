package br.com.mvp.view.dnd.list;

import java.util.Set;

import javax.swing.JList;

import br.com.mvp.util.JListUtil;
import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.DraggingRule;
import br.com.mvp.view.dnd.TransferData;

class JListDragSource<T> implements DragSource<JList<T>> {

	private static final long serialVersionUID = -4132321973108470887L;
	private DraggingRule rule;
	private JListConfiguration<T> conf;

	public JListDragSource(JListConfiguration<T> conf, DraggingRule rule) {
		this.conf = conf;
		this.rule = rule;
	}
	
	@Override
	public TransferData[] createTransferData(JList<T> jList) {
		JListUtil<T> jUtil = new JListUtil<>(jList);
		
		Set<T> selectedElements = jUtil.getSelectedElements();
		TransferData[] transferData = 
				selectedElements
					.stream()
					.filter(t -> conf.getSourceFilter().accept(t) )
					.map(o -> new ListTransferData(o))
					.toArray(size -> new TransferData[size]);

		if(transferData.length == 0)
			return null;
		else
			return transferData;
	}
	
	@Override
	public DraggingRule getRule() {
		return rule;
	}
	
	@Override
	public void dataTransfered(JList<T> jList) {
		
	}

}
