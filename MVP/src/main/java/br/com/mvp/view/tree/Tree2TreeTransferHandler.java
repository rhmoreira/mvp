package br.com.mvp.view.tree;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import br.com.mvp.util.MVPUtil;
import br.com.mvp.view.dnd.DraggingRule;

public class Tree2TreeTransferHandler extends TransferHandler {
	
	private static final long serialVersionUID = 8255284463833775744L;
	
	private DraggingRule rule;

	public Tree2TreeTransferHandler() {
		rule = new Tree2TreeDraggingRule();
	}
	
	@Override
	public int getSourceActions(JComponent c) {
		return Tree2TreeTransferHandler.MOVE;
	}

	@Override
	public boolean canImport(TransferSupport support) {
		
		return rule.isFlavorSupported(support)
				&& rule.isDroppingLocationSupported(support);
	}
	
	@Override
	protected Transferable createTransferable(JComponent c) {
		JTree tree = (JTree) c;
		TreePath[] treePaths = tree.getSelectionPaths();
		
		List<Serializable> nodes = new ArrayList<>();
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePaths[0].getLastPathComponent();
		
		if (node.isLeaf()){
			nodes.add(MVPUtil.copyObject((Serializable)node.getUserObject()));
			
			for (int i=1; i<treePaths.length; i++){
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treePaths[i].getLastPathComponent();
				if (node.getLevel() == selectedNode.getLevel())
					nodes.add(MVPUtil.copyObject((Serializable)selectedNode.getUserObject()));
			}
			Transferable nodeTransfer = new TransferableNode(nodes.toArray());
			
			return nodeTransfer;
		}else
			return null;
	}
	
	@Override
	public boolean importData(TransferSupport support) {
		JTree.DropLocation dropLocation = (JTree.DropLocation)support.getDropLocation();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) dropLocation.getPath().getLastPathComponent();
        
        JTree tree = (JTree) support.getComponent();
		DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
        
        int childIndex = dropLocation.getChildIndex() == -1 ? 0 : dropLocation.getChildIndex();
        
        for (Object newData: getTransferData(support)){
        	if (!childExists(newData, node))
        		treeModel.insertNodeInto(new DefaultMutableTreeNode(newData), node, childIndex);
        }
		
        return true;
	}
	
	private boolean childExists(Object newData, DefaultMutableTreeNode parent) {
		
		for (int i=0; i<parent.getChildCount(); i++){
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) parent.getChildAt(i);
			if (child.getUserObject().equals(newData))
				return true;
		}
			
		return false;
	}

	private Object[] getTransferData(TransferSupport support){
		List<Object> data = new ArrayList<>();
		
		try{
			Transferable transferable = support.getTransferable();
			for (DataFlavor flavor: rule.getFlavors()){
				Object transferData = transferable.getTransferData(flavor);
				if (transferData != null){
					data.addAll(Arrays.asList( (Object[]) transferData));
				}
			}
			return data.toArray();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private class TransferableNode implements Transferable, Serializable{
		
		private static final long serialVersionUID = 849943366639025520L;
		
		Object[] nodes;
		
		private TransferableNode(Object[] nodes) {
			this.nodes = nodes;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return rule.getFlavors();
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return rule.isFlavorSupported(flavor);
		}

		@Override
		public Object[] getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
			if(!isDataFlavorSupported(flavor))
                throw new UnsupportedFlavorException(flavor);
            return nodes;
		}
		
	}
	
}
