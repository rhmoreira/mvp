package br.com.mvp.view.dnd;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.mvp.view.dnd.tree.TreeSubDataExtractor;
import br.com.mvp.view.dnd.tree.TreeTransferData;

public class TransferSubDataExtractor {

	private static Map<Class<? extends TransferData>, SubDataExtractor> extractorMap = new HashMap<>();
	private static final SubDataExtractor defaultExtractor = (transfer) -> Collections.emptyList();
	
	static{
		extractorMap.put(TreeTransferData.class, new TreeSubDataExtractor());
	}
	
	public static Collection<TransferData> extract(TransferData transfer){
		SubDataExtractor extractor = getExtractor(transfer.getClass());
		return extractor.extract(transfer);
	}
	
	private static SubDataExtractor getExtractor(Class<? extends TransferData> dataClass){
		SubDataExtractor subDataExtractor = extractorMap.get(dataClass);
		if (subDataExtractor == null)
			subDataExtractor = defaultExtractor;
		
		return subDataExtractor;
	}
	
}
