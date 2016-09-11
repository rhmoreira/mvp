package br.com.mvp.view.dnd.tree;

import javax.swing.tree.MutableTreeNode;

public enum TreeTransferMethod {

	LEAFS_ONLY,
	NODES_ONLY,
	NODES_AND_CHILDREN,
	;
	
	public boolean accept(MutableTreeNode node){
		switch (this) {
		case LEAFS_ONLY:
			return node.isLeaf();
		case NODES_ONLY:
			return !node.isLeaf();
		case NODES_AND_CHILDREN:
			return true;
		default:
			return false;
		}
	}
}
