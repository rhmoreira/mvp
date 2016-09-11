package br.com.mvp.view.dnd;

import java.io.Serializable;

import javax.swing.JComponent;

public interface DragSource<C extends JComponent> extends Serializable {

	TransferData[] createTransferData(C component);
}