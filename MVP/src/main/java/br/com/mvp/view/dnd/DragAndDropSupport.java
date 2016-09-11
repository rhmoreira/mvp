package br.com.mvp.view.dnd;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class DragAndDropSupport<S extends JComponent, D extends JComponent> {

	private TransferDataContent transferDataContent = TransferDataContent.REFERENCE;
	private DragSource<S> source;
	private DropDestination<D> dest;
	private DraggingRule rule;
	
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
			ComponentDragAndDropSupport<D> destSupport, DraggingRule rule) {
		this(sourceSupport.createSource(), destSupport.createDestination());
		this.rule = rule;
	}

	public DragAndDropSupport<S,D> setDataContentPacking(TransferDataContent dragObj){
		this.transferDataContent = dragObj;
		return this;
	}
	
	public DragAndDropSupport<S,D> useRule(DraggingRule rule){
		this.rule = rule;
		return this;
	}
	
	public TransferHandler createTransferHandler(){
		DndTransferHandler<S, D> th = new DndTransferHandler<S, D>(source, dest, rule, transferDataContent);
		return th;
	}
	
}
