package br.com.mvp.view.dnd;

import java.awt.datatransfer.DataFlavor;

import javax.swing.TransferHandler.TransferSupport;

public interface DraggingRule {

	boolean isFlavorSupported(TransferSupport support);
	boolean isFlavorSupported(DataFlavor flavor);
	boolean isDroppingLocationSupported(TransferSupport support);
	DataFlavor[] getFlavors();
}
