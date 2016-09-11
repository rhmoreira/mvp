package br.com.mvp.view.dnd.tree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.TransferData;

class JTreeDragSource implements DragSource<JTree>{
	
	private static final long serialVersionUID = 8255284463833775744L;
	
	private TreeTransferMethod method;
	
	public JTreeDragSource(TreeTransferMethod method) {
		this.method = method;
	}

	@Override
	public TransferData[] createTransferData(JTree tree) {
		
		TreePath[] treePaths = tree.getSelectionPaths();
		
		List<TransferData> nodes = new ArrayList<>();
		
		for (TreePath path: treePaths){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			if (method.accept(node)){
				TreeTransferData transferData = new TreeTransferData(node.getUserObject());
				nodes.add(transferData);
				addChildrenByMethod(transferData, node);
			}
		}
			
		if (nodes.isEmpty())
			return null;
		else
			return nodes.toArray(new TransferData[nodes.size()]);
	}
	
	private void addChildrenByMethod(TreeTransferData data, DefaultMutableTreeNode node){
		if (method == TreeTransferMethod.NODES_AND_CHILDREN){
			data.setChildren(new ArrayList<>());
			for (int i = 0; i < node.getChildCount(); i++){
				DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
				TreeTransferData childData = new TreeTransferData(child.getUserObject());
				data.getChildren().add(childData);
				addChildrenByMethod(childData, child);
			}
		}
	}
}
