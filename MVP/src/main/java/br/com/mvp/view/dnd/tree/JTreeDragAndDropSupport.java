package br.com.mvp.view.dnd.tree;

import javax.swing.JTree;

import br.com.mvp.view.dnd.ComponentDragAndDropSupport;
import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.DraggingRule;
import br.com.mvp.view.dnd.DropDestination;

public class JTreeDragAndDropSupport implements ComponentDragAndDropSupport<JTree> {
	
	private static final long serialVersionUID = -5392903876000869158L;
	
	private TreeConfiguration conf;
	private TreeDraggingRule rule;
	
	/**
	 * Creates a <code>JTreeDragAndDropSupport</code> with a default <code>TreeTransferMethod.LEAFS_ONLY</code>
	 */
	
	public JTreeDragAndDropSupport() {
		this(new TreeConfiguration(TreeTransferMethod.LEAFS_ONLY, false, -1));
	}
	
	public JTreeDragAndDropSupport(TreeConfiguration treeConfiguration) {
		this.conf = treeConfiguration;
		this.rule = new TreeDraggingRule(treeConfiguration);
	}

	public DragSource<JTree> createSource(){
		return createSource(conf);
	}
	
	public DropDestination<JTree> createDestination(){
		return createDestination(conf);
	}
	
	public DraggingRule createRule(){
		return createRule(conf);
	}
	
	/**
	 * Creates a <code>DragSource</code> and overrides the default <code>TreeTransferMethod</code> or the one supplied in the constructor 
	 */
	public DragSource<JTree> createSource(TreeConfiguration treeConfiguration){
		return new JTreeDragSource(treeConfiguration.getMethod(), rule);
	}
	
	/**
	 * Creates a <code>DropDestination</code> and overrides the default <code>TreeTransferMethod</code> or the one supplied in the constructor 
	 */
	public DropDestination<JTree> createDestination(TreeConfiguration treeConfiguration){
		return new JTreeDropDestination(treeConfiguration.getMethod(), rule);
	}
	
	/**
	 * Overrides the default <code>TreeTransferMethod</code> or the one supplied in the constructor 
	 */
	public DraggingRule createRule(TreeConfiguration treeConfiguration){
		return rule;
	}
}
