package br.com.mvp.view.dnd.tree;

import br.com.mvp.view.dnd.configuration.DnDConfiguration;
import br.com.mvp.view.dnd.configuration.ElementFilter;

public class TreeConfiguration extends DnDConfiguration<Object>{

	private TreeTransferMethod method;
	private boolean rootAccess;
	private int maxDepth;
	
	public TreeConfiguration() {
	}
	
	public TreeConfiguration(TreeTransferMethod method, boolean rootAccess, int maxDepth) {
		super();
		this.method = method;
		this.rootAccess = rootAccess;
		this.maxDepth = maxDepth;
	}
	
	TreeTransferMethod getMethod() {
		return method;
	}
	boolean isRootAccess() {
		return rootAccess;
	}
	int getMaxDepth() {
		return maxDepth;
	}
		
	public TreeConfiguration method(TreeTransferMethod method){
		this.method = method;
		return this;
	}
	
	public TreeConfiguration maxDepth(int depth){
		this.maxDepth = depth;
		return this;
	}
	
	public TreeConfiguration rootAccessible(boolean access){
		this.rootAccess = access;
		return this;
	}

	@Override
	public TreeConfiguration sourceFilter(ElementFilter<Object> filter) {
		super.sourceFilter(filter);
		return this;
	}

	@Override
	public TreeConfiguration destFilter(ElementFilter<Object> filter) {
		super.destFilter(filter);
		return this;
	}
	
}
