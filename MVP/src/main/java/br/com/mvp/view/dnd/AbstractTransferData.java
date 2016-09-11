package br.com.mvp.view.dnd;

public class AbstractTransferData implements TransferData {

	private static final long serialVersionUID = -1075077178119105420L;
	
	private Object data;
	
	public AbstractTransferData(Object data) {
		super();
		this.data = data;
	}

	@Override
	public Object getData() {
		return data;
	}

}
