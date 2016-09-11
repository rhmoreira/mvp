package br.com.mvp.view.dnd;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class DragAndDropSupport<S extends JComponent, D extends JComponent> {

	private DraggingRule rule;
	private TransferDataContent transferDataContent = TransferDataContent.REFERENCE;
	private DragSource<S> source;
	private DropDestination<D> dest;
	
	public DragAndDropSupport(DropDestination<D> dest) {
		this(null, dest);
	}
	
	public DragAndDropSupport(DragSource<S> source) {
		this(source, null);
	}
	
	public DragAndDropSupport(DragSource<S> source, DropDestination<D> dest) {
		this.source = source;
		this.dest = dest;
	}
	
	public void setDataContentPacking(TransferDataContent dragObj){
		this.transferDataContent = dragObj;
	}
	
	public void useRule(DraggingRule rule){
		this.rule = rule;
	}
	
	public TransferHandler createTransferHandler(){
		DndTransferHandler<S, D> th = new DndTransferHandler<S, D>(source, dest, rule, transferDataContent);
		return th;
	}
	
}
