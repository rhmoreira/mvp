package br.com.mvp.view.dnd.tree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.DraggingRule;
import br.com.mvp.view.dnd.TransferData;

class JTreeDragSource implements DragSource<JTree>{
	
	private static final long serialVersionUID = 8255284463833775744L;
	
	private TreeConfiguration conf;
	private DraggingRule rule;
	
	public JTreeDragSource(TreeConfiguration conf, DraggingRule rule) {
		this.conf = conf;
		this.rule = rule;
	}

	@Override
	public TransferData[] createTransferData(JTree tree) {
		
		TreePath[] treePaths = tree.getSelectionPaths();
		
		List<TransferData> nodes = new ArrayList<>();
		
		for (TreePath path: treePaths){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			if (conf.getMethod().accept(node)){
				if (conf.getSourceFilter().accept(node.getUserObject())){
					TreeTransferData transferData = new TreeTransferData(node.getUserObject());
					nodes.add(transferData);
					addChildrenByTransferMethod(transferData, node);
				}
			}
		}
			
		if (nodes.isEmpty())
			return null;
		else
			return nodes.toArray(new TransferData[nodes.size()]);
	}
	
	private void addChildrenByTransferMethod(TreeTransferData data, DefaultMutableTreeNode node){
		if (conf.getMethod() == TreeTransferMethod.NODES_AND_LEAFS){
			data.setChildren(new ArrayList<>());
			for (int i = 0; i < node.getChildCount(); i++){
				DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
				TreeTransferData childData = new TreeTransferData(child.getUserObject());
				data.getChildren().add(childData);
				addChildrenByTransferMethod(childData, child);
			}
		}
	}
	
	@Override
	public DraggingRule getRule() {
		return rule;
	}

	@Override
	public void dataTransfered(JTree component) {
		
	}
	
}
