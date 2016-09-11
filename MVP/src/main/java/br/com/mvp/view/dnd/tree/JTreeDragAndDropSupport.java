package br.com.mvp.view.dnd.tree;

import javax.swing.JTree;

import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.DraggingRule;
import br.com.mvp.view.dnd.DropDestination;

public class JTreeDragAndDropSupport {
	
	private TreeTransferMethod method;
	
	/**
	 * Creates a <code>JTreeDragAndDropSupport</code> with a default <code>TreeTransferMethod.LEAFS_ONLY</code>
	 */
	public JTreeDragAndDropSupport() {
		this(TreeTransferMethod.LEAFS_ONLY);
	}
	
	public JTreeDragAndDropSupport(TreeTransferMethod method) {
		this.method = method;
	}

	public DragSource<JTree> createSource(){
		return createSource(method);
	}
	
	public DropDestination<JTree> createDestination(){
		return createDestination(method);
	}
	
	public DraggingRule createRule(){
		return createRule(method);
	}
	
	/**
	 * Creates a <code>DragSource</code> and overrides the default <code>TreeTransferMethod</code> or the one supplied in the constructor 
	 */
	public DragSource<JTree> createSource(TreeTransferMethod method){
		return new JTreeDragSource(method);
	}
	
	/**
	 * Creates a <code>DropDestination</code> and overrides the default <code>TreeTransferMethod</code> or the one supplied in the constructor 
	 */
	public DropDestination<JTree> createDestination(TreeTransferMethod method){
		return new JTreeDropDestination(method);
	}
	
	/**
	 * Overrides the default <code>TreeTransferMethod</code> or the one supplied in the constructor 
	 */
	public DraggingRule createRule(TreeTransferMethod method){
		return new TreeDraggingRule(method);
	}
}
