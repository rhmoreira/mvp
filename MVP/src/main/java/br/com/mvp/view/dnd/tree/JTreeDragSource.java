package br.com.mvp.view.dnd.tree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.TransferData;

public class JTreeDragSource implements DragSource<JTree>{
	
	private static final long serialVersionUID = 8255284463833775744L;
	
	@Override
	public TransferData[] createTransferData(JTree tree) {
		
		TreePath[] treePaths = tree.getSelectionPaths();
		
		List<TransferData> nodes = new ArrayList<>();
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePaths[0].getLastPathComponent();
		
		if (node.isLeaf()){
			nodes.add(new TreeTransferData(node.getUserObject()));
			
			for (int i=1; i<treePaths.length; i++){
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treePaths[i].getLastPathComponent();
				if (node.getLevel() == selectedNode.getLevel())
					nodes.add(new TreeTransferData(selectedNode.getUserObject()));
			}
			
			return nodes.toArray(new TransferData[nodes.size()]);
		}else
			return null;
	}
}
