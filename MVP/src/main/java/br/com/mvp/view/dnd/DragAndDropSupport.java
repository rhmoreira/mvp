package br.com.mvp.view.dnd;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class DragAndDropSupport<S extends JComponent, D extends JComponent> {

	private TransferDataContent transferDataContent = TransferDataContent.REFERENCE;
	private DragSource<S> source;
	private DropDestination<D> dest;
	
	public DragAndDropSupport(DragSource<S> source) {
		this(source, null);
	}
	
	public DragAndDropSupport(DropDestination<D> dest) {
		this(null, dest);
	}
	
	public DragAndDropSupport(DragSource<S> source, DropDestination<D> dest) {
		this.source = source;
		this.dest = dest;
	}
	
	public DragAndDropSupport(ComponentDragAndDropSupport<S> sourceSupport,
			ComponentDragAndDropSupport<D> destSupport) {
		this(sourceSupport.createSource(), destSupport.createDestination());
	}

	public DragAndDropSupport<S,D> setDataContentPacking(TransferDataContent dragObj){
		this.transferDataContent = dragObj;
		return this;
	}
	
	public TransferHandler createTransferHandler(){
		DnDTransferHandler<S, D> th = new DnDTransferHandler<S, D>(source, dest, transferDataContent);
		return th;
	}
	
}
