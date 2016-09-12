package br.com.mvp.view.dnd.tree;

import javax.swing.JTree;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import br.com.mvp.view.dnd.DropDestination;
import br.com.mvp.view.dnd.TransferData;

class JTreeDropDestination implements DropDestination<JTree> {
	
	private static final long serialVersionUID = -864163629272306113L;

	private TreeTransferMethod method;
	
	public JTreeDropDestination(TreeTransferMethod method) {
		this.method = method;
	}

	@Override
	public void dropData(JTree tree, TransferSupport support, TransferData[] transferDataArray) {
		JTree.DropLocation dropLocation = (JTree.DropLocation)support.getDropLocation();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) dropLocation.getPath().getLastPathComponent();
        
		DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
        
        int childIndex = dropLocation.getChildIndex() == -1 ? 0 : dropLocation.getChildIndex();
        
        for (TransferData transferData: transferDataArray){
        	DefaultMutableTreeNode child = getChildIfExists(transferData.getData(), node);
        	if (child == null) {
        		child = new DefaultMutableTreeNode(transferData.getData());
				treeModel.insertNodeInto(child, node, childIndex++);
			}
        	
        	insertNodeByMethod(treeModel, child, transferData, childIndex);
        }
	}
	
	private void insertNodeByMethod(DefaultTreeModel treeModel, DefaultMutableTreeNode parent, TransferData transferData, int childIndex) {
		if (method == TreeTransferMethod.NODES_AND_CHILDREN 
				&& transferData instanceof TreeTransferData){
			TreeTransferData treeTransferData = (TreeTransferData) transferData;
			
			if (treeTransferData.hasChildren()){
				int i = 0;
				for (TreeTransferData childData: treeTransferData.getChildren()){
					DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(childData.getData());
					treeModel.insertNodeInto(childNode, parent, i++);
					insertNodeByMethod(treeModel, childNode, childData, childIndex);
				}
			}
		}
	}

	private DefaultMutableTreeNode getChildIfExists(Object newData, DefaultMutableTreeNode parent) {
		for (int i=0; i<parent.getChildCount(); i++){
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) parent.getChildAt(i);
			if (child.getUserObject().equals(newData))
				return child;
		}
		return null;
	}

}
