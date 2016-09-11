package br.com.mvp.view.dnd.tree;

import java.util.List;

import br.com.mvp.view.dnd.TransferData;

public class TreeTransferData implements TransferData{

	private static final long serialVersionUID = -3990866164244894384L;
	
	private Object userObject;	
	private List<TreeTransferData> children;
	
	public TreeTransferData(Object userObject) {
		super();
		this.userObject = userObject;
	}
	
	public Object getUserObject() {
		return userObject;
	}
	public void setUserObject(Object userObject) {
		this.userObject = userObject;
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
	
	@Override
	public Object getData() {
		return userObject;
	}
}
