package br.com.mvp.view.dnd.tree;

import javax.swing.JTree;

import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.DraggingRule;
import br.com.mvp.view.dnd.DropDestination;

public abstract class JTreeDragAndDropDSupport {
	
	private JTreeDragAndDropDSupport() {}

	public static DragSource<JTree> createSource(){
		return new JTreeDragSource();
	}
	
	public static DropDestination<JTree> createDestination(){
		return new JTreeDropDestination();
	}
	
	public static DraggingRule createRule(){
		return new Tree2TreeDraggingRule();
	}
}
