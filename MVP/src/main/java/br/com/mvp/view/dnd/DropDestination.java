package br.com.mvp.view.dnd;

import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.TransferHandler.TransferSupport;

public interface DropDestination<C extends JComponent> extends Serializable {

	void dropData(C component, TransferSupport support, TransferData[] data);
}
