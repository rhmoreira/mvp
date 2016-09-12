package br.com.mvp.view.dnd.tree;

import java.util.ArrayList;
import java.util.Collection;

import br.com.mvp.view.dnd.SubDataExtractor;
import br.com.mvp.view.dnd.TransferData;

public class TreeSubDataExtractor implements SubDataExtractor {

	@Override
	public Collection<TransferData> extract(TransferData data) {
		TreeTransferData treeData = (TreeTransferData) data;
		Collection<TransferData> subData = new ArrayList<>();
		
		extract(subData, treeData);
		
		return subData;
	}
	
	private void extract(Collection<TransferData> subData, TreeTransferData data){
		if (data.hasChildren()){
			for (TreeTransferData child: data.getChildren()){
				subData.add(child);
				extract(child);
			}
		}
	}

}
