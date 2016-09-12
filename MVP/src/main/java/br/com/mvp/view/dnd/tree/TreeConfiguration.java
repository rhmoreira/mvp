package br.com.mvp.view.dnd.tree;

public class TreeConfiguration {

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
	void setMethod(TreeTransferMethod method) {
		this.method = method;
	}
	boolean isRootAccess() {
		return rootAccess;
	}
	void setRootAccess(boolean rootAccess) {
		this.rootAccess = rootAccess;
	}
	int getMaxDepth() {
		return maxDepth;
	}
	void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
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
	
}
