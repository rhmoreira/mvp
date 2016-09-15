package br.com.mvp.view.dnd.tree;

import javax.swing.JTree;
import javax.swing.TransferHandler.DropLocation;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.com.mvp.view.dnd.DefaultDragginRule;

class TreeDraggingRule extends DefaultDragginRule {
	
	private static final long serialVersionUID = -2032817379458183541L;
	
	private TreeConfiguration conf;
	
	public TreeDraggingRule(TreeConfiguration treeConfiguration) {
		this.conf = treeConfiguration;
	}

	@Override
	public boolean isDroppingLocationSupported(TransferSupport support) {
		DropLocation location = support.getDropLocation();
		if (support.isDrop() && location instanceof JTree.DropLocation){
			TreePath path = ((JTree.DropLocation)location).getPath();
			if (path == null)
				return false;
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			if (node.isRoot() && !conf.isRootAccess())
				return false;
			else
				return conf.getMethod().accept(node)
						&& (conf.getMaxDepth() == -1 ? true : node.getLevel() == conf.getMaxDepth())
						&& conf.getDestFilter().accept(node.getUserObject());
		}else
			return false;
	}
	
}
