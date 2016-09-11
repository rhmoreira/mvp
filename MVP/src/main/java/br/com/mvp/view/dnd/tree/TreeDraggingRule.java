package br.com.mvp.view.dnd.tree;

import javax.swing.JTree;
import javax.swing.TransferHandler.DropLocation;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.com.mvp.view.dnd.DefaultDragginRule;

class TreeDraggingRule extends DefaultDragginRule {
	
	private static final long serialVersionUID = -2032817379458183541L;
	
	private TreeTransferMethod method;
	
	public TreeDraggingRule(TreeTransferMethod method) {
		this.method = method;
	}

	@Override
	public boolean isDroppingLocationSupported(TransferSupport support) {
		DropLocation location = support.getDropLocation();
		if (support.isDrop() && location instanceof JTree.DropLocation){
			TreePath path = ((JTree.DropLocation)location).getPath();
			if (path == null)
				return false;
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			return method.accept(node);					
		}else
			return false;
	}
	
}
