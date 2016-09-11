package br.com.mvp.view.dnd.tree;

import java.awt.datatransfer.DataFlavor;

import javax.swing.JTree;
import javax.swing.TransferHandler.DropLocation;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.com.mvp.view.dnd.DraggingRule;

public class Tree2TreeDraggingRule implements DraggingRule {
	
	private static final long serialVersionUID = -2032817379458183541L;

	@Override
	public boolean isFlavorSupported(TransferSupport support) {
		boolean supports = false;
		for (DataFlavor df: getFlavors())
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
	public boolean isFlavorSupported(DataFlavor flavor) {
		boolean supports = false;
		for (DataFlavor df: getFlavors())
			supports = supports || df.equals(flavor);
		return supports;
	}

}
