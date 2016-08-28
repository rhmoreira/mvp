package br.com.mvp.view;

import java.util.Iterator;

import javax.swing.JPanel;

public interface ViewMapper<V extends JPanel> extends Iterator<ViewClassHandler<V>>{

	Iterator<ViewClassHandler<V>> iterator();
	
	boolean hasDependency();
	
	Iterator<ViewMapper<V>> iterateDependencies();
}
