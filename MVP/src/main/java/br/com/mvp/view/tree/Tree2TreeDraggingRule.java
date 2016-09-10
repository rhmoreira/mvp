package br.com.mvp.view.tree;

import java.awt.datatransfer.DataFlavor;

import javax.swing.JTree;
import javax.swing.TransferHandler.DropLocation;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.com.mvp.view.dnd.DraggingRule;

public class Tree2TreeDraggingRule implements DraggingRule {
	
	private DataFlavor[] TREE_NODE_DATAFLAVOR = new DataFlavor[1];
	
	public Tree2TreeDraggingRule() {
		try{
			TREE_NODE_DATAFLAVOR[0] = 
					new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + 
							";class=" + TreePart.class.getName());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isFlavorSupported(TransferSupport support) {
		boolean supports = false;
		for (DataFlavor df: TREE_NODE_DATAFLAVOR)
			supports = supports || support.isDataFlavorSupported(df);
		return supports;
	}

	@Override
	public boolean isDroppingLocationSupported(TransferSupport support) {
		DropLocation location = support.getDropLocation();
		if (support.isDrop() && location instanceof JTree.DropLocation){
			TreePath path = ((JTree.DropLocation)location).getPath();
			if (path == null)
				return false;
			
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			return !node.isLeaf();					
		}else
			return false;
	}
	
	@Override
	public DataFlavor[] getFlavors() {
		return TREE_NODE_DATAFLAVOR;
	}

	@Override
	public boolean isFlavorSupported(DataFlavor flavor) {
		boolean supports = false;
		for (DataFlavor df: TREE_NODE_DATAFLAVOR)
			supports = supports || df.equals(flavor);
		return supports;
	}

}
