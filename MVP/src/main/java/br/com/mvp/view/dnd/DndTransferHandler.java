package br.com.mvp.view.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import br.com.mvp.util.MVPUtil;
import br.com.mvp.view.dnd.tree.Tree2TreeTransferHandler;

class DndTransferHandler<S extends JComponent, D extends JComponent> extends TransferHandler {

	private static final long serialVersionUID = -6893769314663493770L;
	
	private DraggingRule rule;
	private TransferDataContent transferDataContent;
	private DragSource<S> source;
	private DropDestination<D> dest;
	
	public DndTransferHandler(DragSource<S> source, DropDestination<D> dest, DraggingRule rule, TransferDataContent transferDataContent) {
		super();
		this.rule = rule;
		this.transferDataContent = transferDataContent;
		this.source = source;
		this.dest = dest;
	}
	
	@Override
	public int getSourceActions(JComponent c) {
		return Tree2TreeTransferHandler.COPY_OR_MOVE;
	}
	
	@Override
	protected Transferable createTransferable(JComponent c) {
		if (source != null){
			TransferData[] data = source.createTransferData((S) c);
			
			if (data != null){
				if (transferDataContent == TransferDataContent.COPY) 
					data = MVPUtil.copyObject(data);
				Transferable t = new DnDTransferable(data);
				return t;
			}else
				return null;
		}else
			return null;
	}

	@Override
	public boolean canImport(TransferSupport support) {
		if (rule != null)
			return rule.isFlavorSupported(support)
					&& rule.isDroppingLocationSupported(support);
		else
			return false;
	}
	
	@Override
	public boolean importData(TransferSupport support) {
		TransferData[] transferData = getTransferData(support);
		dest.dropData((D) support.getComponent(), support, transferData);
        return true;
	}
	
	private TransferData[] getTransferData(TransferSupport support){
		try{
			Transferable transferable = support.getTransferable();
			for (DataFlavor flavor: rule.getFlavors()){
				TransferData[] transferData = (TransferData[]) transferable.getTransferData(flavor);
				if (transferData != null)
					return transferData;
			}
			return null;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public class DnDTransferable implements Transferable, Serializable{
		
		private static final long serialVersionUID = 849943366639025520L;
		
		TransferData[] data;
		
		private DnDTransferable(TransferData[] data) {
			this.data = data;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return rule.getFlavors();
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return rule.isFlavorSupported(flavor);
		}

		@Override
		public Object[] getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
			if(!isDataFlavorSupported(flavor))
                throw new UnsupportedFlavorException(flavor);
            return data;
		}
	}
}
