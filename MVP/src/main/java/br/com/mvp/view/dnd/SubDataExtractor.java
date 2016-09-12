package br.com.mvp.view.dnd;

import java.util.Collection;

public interface SubDataExtractor {
	
	Collection<TransferData> extract(TransferData data);

}
