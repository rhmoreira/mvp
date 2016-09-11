package br.com.mvp.view.dnd;

public enum TransferDataContent {

	REFERENCE,
	/**
	 * To use this option, the data objects must implement <code>Serializable</code>
	 */
	COPY,
	;
}