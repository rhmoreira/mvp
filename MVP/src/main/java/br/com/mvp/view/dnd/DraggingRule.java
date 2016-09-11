package br.com.mvp.view.dnd;

import java.awt.datatransfer.DataFlavor;
import java.io.Serializable;

import javax.swing.TransferHandler.TransferSupport;

public interface DraggingRule extends Serializable {

	boolean isFlavorSupported(TransferSupport support);
	boolean isFlavorSupported(DataFlavor flavor);
	boolean isDroppingLocationSupported(TransferSupport support);
	
	default DataFlavor[] getFlavors(){
		try{
			final DataFlavor[] TREE_NODE_DATAFLAVOR = {
					new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + 
							";class=" + TransferData.class.getName())};
			return TREE_NODE_DATAFLAVOR;
		}catch (Exception e) {
			return null;
		}
	}
}
