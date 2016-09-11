package br.com.mvp.view.dnd.tree;

import javax.swing.JTree;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import br.com.mvp.view.dnd.DropDestination;
import br.com.mvp.view.dnd.TransferData;

public class JTreeDropDestination implements DropDestination<JTree> {
	
	private static final long serialVersionUID = -864163629272306113L;

	@Override
	public void dropData(JTree tree, TransferSupport support, TransferData[] transferDataArray) {
		JTree.DropLocation dropLocation = (JTree.DropLocation)support.getDropLocation();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) dropLocation.getPath().getLastPathComponent();
        
		DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
        
        int childIndex = dropLocation.getChildIndex() == -1 ? 0 : dropLocation.getChildIndex();
        
        for (TransferData transferData: transferDataArray){
        	if (!childExists(transferData.getData(), node))
        		treeModel.insertNodeInto(new DefaultMutableTreeNode(transferData.getData()), node, childIndex);
        }
	}
	
	private boolean childExists(Object newData, DefaultMutableTreeNode parent) {
		
		for (int i=0; i<parent.getChildCount(); i++){
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) parent.getChildAt(i);
			if (child.getUserObject().equals(newData))
				return true;
		}
			
		return false;
	}

}
