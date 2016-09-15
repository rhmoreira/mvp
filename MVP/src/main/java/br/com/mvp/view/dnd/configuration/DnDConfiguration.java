package br.com.mvp.view.dnd.configuration;

import javax.swing.TransferHandler;

public abstract class DnDConfiguration<E> {

	public enum SourceAction {
		COPY(TransferHandler.COPY),
		MOVE(TransferHandler.MOVE),
		COPY_OR_MOVE(TransferHandler.COPY_OR_MOVE),
		LINK(TransferHandler.LINK),
		;
		
		private int swingAction;
		
		private SourceAction(int swingAction) {
			this.swingAction = swingAction;			
		}
		
		public int getAction(){
			return swingAction;
		}
	}
	
	private SourceAction action;
	private ElementFilter<E> sourceFilter = (e) -> true;
	private ElementFilter<E> destFilter = (e) -> true;
	
	
	public DnDConfiguration() {
		action(SourceAction.COPY);
	}

	public DnDConfiguration<E> action(SourceAction action){
		this.action = action;
		return this;
	}
	public SourceAction getAction() {
		return action;
	}
	protected DnDConfiguration<E> sourceFilter(ElementFilter<E> filter){
		this.sourceFilter = filter;
		return this;
	}
	public ElementFilter<E> getSourceFilter() {
		return sourceFilter;
	}
	
	protected DnDConfiguration<E> destFilter(ElementFilter<E> filter){
		this.destFilter = filter;
		return this;
	}
	public ElementFilter<E> getDestFilter() {
		return destFilter;
	}
}
