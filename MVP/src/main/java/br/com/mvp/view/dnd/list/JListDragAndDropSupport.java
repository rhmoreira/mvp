package br.com.mvp.view.dnd.list;

import javax.swing.JList;

import br.com.mvp.view.dnd.ComponentDragAndDropSupport;
import br.com.mvp.view.dnd.DefaultDragginRule;
import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.DraggingRule;
import br.com.mvp.view.dnd.DropDestination;

public class JListDragAndDropSupport<T> implements ComponentDragAndDropSupport<JList<T>> {

	private static final long serialVersionUID = 998732210369698999L;

	public JListDragAndDropSupport() {
	}
	
	public DragSource<JList<T>> createSource(){
		return new JListDragSource<T>(DefaultDragginRule.instance());
	}
	
	public DropDestination<JList<T>> createDestination(){
		return new JListDropDestination<T>(DefaultDragginRule.instance());
	}
	
	public DraggingRule createRule(){
		return DefaultDragginRule.instance();
	}
}
