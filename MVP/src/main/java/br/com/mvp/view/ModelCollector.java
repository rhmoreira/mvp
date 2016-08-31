package br.com.mvp.view;

import javax.swing.JList;
import javax.swing.ListModel;

public enum ModelCollector {
	
	/**
	 * Only the selected elements within the {@link JList} component
	 */
	SELECTED,
	
	/**
	 * All elements contained in the {@link ListModel} component
	 */
	VALUES,
	;

}
