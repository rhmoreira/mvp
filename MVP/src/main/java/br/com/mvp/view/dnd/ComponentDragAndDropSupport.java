package br.com.mvp.view.dnd;

import javax.swing.JComponent;

public abstract class ComponentDragAndDropSupport<T extends JComponent> {

	public abstract DragSource<T> createSource();
	public abstract DropDestination<T> createDestination();
	public abstract DraggingRule createRule();
}
