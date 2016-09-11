package br.com.mvp.view.dnd.list;

import javax.swing.JList;

import br.com.mvp.view.dnd.ComponentDragAndDropSupport;
import br.com.mvp.view.dnd.DefaultDragginRule;
import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.DraggingRule;
import br.com.mvp.view.dnd.DropDestination;

public class JListDragAndDropSupport<T> extends ComponentDragAndDropSupport<JList<T>> {

	public JListDragAndDropSupport() {
	}
	
	public DragSource<JList<T>> createSource(){
		return new JListDragSource<T>();
	}
	
	public DropDestination<JList<T>> createDestination(){
		return new JListDropDestination<T>();
	}
	
	public DraggingRule createRule(){
		return new DefaultDragginRule();
	}
}
