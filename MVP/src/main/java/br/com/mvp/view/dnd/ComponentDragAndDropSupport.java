package br.com.mvp.view.dnd;

import java.io.Serializable;

import javax.swing.JComponent;

public interface ComponentDragAndDropSupport<T extends JComponent> extends Serializable{

	DragSource<T> createSource();
	DropDestination<T> createDestination();
	DraggingRule createRule();
}
