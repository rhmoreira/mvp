package br.com.mvp.view.dnd.tree;

import java.util.List;

import br.com.mvp.view.dnd.AbstractTransferData;

public class TreeTransferData extends AbstractTransferData{

	private static final long serialVersionUID = 8328374803913842210L;
	
	private List<TreeTransferData> children	;
	
	public TreeTransferData(Object data) {
		super(data);
	}
	
	public List<TreeTransferData> getChildren() {
		return children;
	}
	public void setChildren(List<TreeTransferData> children) {
		this.children = children;
	}
	
	public boolean hasChildren(){
		return children != null && !children.isEmpty();
	}
}
