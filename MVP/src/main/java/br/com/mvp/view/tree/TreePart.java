package br.com.mvp.view.tree;

import java.io.Serializable;
import java.util.List;

public class TreePart implements Serializable{

	private static final long serialVersionUID = -3990866164244894384L;
	
	private Serializable userObject;	
	private List<TreePart> children;
	
	public Serializable getUserObject() {
		return userObject;
	}
	public void setUserObject(Serializable userObject) {
		this.userObject = userObject;
	}
	public List<TreePart> getChildren() {
		return children;
	}
	public void setChildren(List<TreePart> children) {
		this.children = children;
	}
	
	public boolean hasChildren(){
		return children != null && !children.isEmpty();
	}
}
