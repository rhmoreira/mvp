package br.com.mvp.view.dnd.list;

import javax.swing.JList;

import br.com.mvp.view.dnd.ComponentDragAndDropSupport;
import br.com.mvp.view.dnd.DefaultDragginRule;
import br.com.mvp.view.dnd.DragSource;
import br.com.mvp.view.dnd.DropDestination;

public class JListDragAndDropSupport<T> implements ComponentDragAndDropSupport<JList<T>> {

	private static final long serialVersionUID = 998732210369698999L;

	private JListConfiguration<T> conf;
	
	public JListDragAndDropSupport() {
		this(new JListConfiguration<>());
	}
	
	private JListDragAndDropSupport(JListConfiguration<T> configuration) {
		this.conf = configuration;
	}
	
	public DragSource<JList<T>> createSource(){
		return new JListDragSource<T>(conf, DefaultDragginRule.instance());
	}
	
	public DropDestination<JList<T>> createDestination(){
		return new JListDropDestination<T>(conf, DefaultDragginRule.instance());
	}
	
	public JListConfiguration<T> getConfiguration(){
		return conf;
	}
}
