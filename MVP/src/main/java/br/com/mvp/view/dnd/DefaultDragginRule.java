package br.com.mvp.view.dnd;

import java.awt.datatransfer.DataFlavor;

import javax.swing.TransferHandler.TransferSupport;

public class DefaultDragginRule implements DraggingRule {

	private static final long serialVersionUID = -217168025006506294L;

	@Override
	public boolean isFlavorSupported(TransferSupport support) {
		boolean supports = false;
		for (DataFlavor df: getFlavors())
			supports = supports || support.isDataFlavorSupported(df);
		return supports;
	}

	@Override
	public boolean isDroppingLocationSupported(TransferSupport support) {
		return true;
	}
	
	@Override
	public boolean isFlavorSupported(DataFlavor flavor) {
		boolean supports = false;
		for (DataFlavor df: getFlavors())
			supports = supports || df.equals(flavor);
		return supports;
	}

}
