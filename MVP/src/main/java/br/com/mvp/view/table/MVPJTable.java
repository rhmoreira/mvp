package br.com.mvp.view.table;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

import br.com.mvp.view.table.mapper.TableMapper;

@SuppressWarnings("unchecked")
public class MVPJTable<M> extends JTable {

	private static final long serialVersionUID = 4324396294056654135L;

	private String noRowsMessage;
	
	public MVPJTable(TableMapper<M> mapper) {
		this(mapper, new ArrayList<>());
	}
	
	public MVPJTable(TableMapper<M> mapper, List<M> modelList) {
		this(mapper, modelList, "No records found.");
	}
	
	public MVPJTable(TableMapper<M> mapper, String noRowsMessage) {
		this(mapper, new ArrayList<>(), noRowsMessage);
	}
	
	public MVPJTable(TableMapper<M> mapper, List<M> modelList, String noRowsMessage) {
		super(new MVPTableModel<>(mapper, modelList));
		this.noRowsMessage = noRowsMessage;
		setAutoCreateRowSorter(true);
		setFillsViewportHeight(true);
	}
	
	public void setDefaultComboBoxRenderer(Class<?> clazz, Object[] values){
		setDefaultRenderer(clazz, new ComboBoxCellRenderer(new JComboBox<>(values)));
		setDefaultEditor(clazz, new DefaultCellEditor(new JComboBox<>(values)));
	}
	
	public M getSelectedRowDatum() {
		int row = getSelectedRow();
		if (row == -1)
			return null;
		
		return getModel().getModelList().get(convertRowIndexToModel(row));
	}
	
	public void removeRow(int row) {
		getModel().removeRow(row);
	}
	
	public void removeRows(int[] rows) {
		Arrays
			.stream(rows)
			.boxed()
			.map(row -> convertRowIndexToModel(row))
			.sorted((i1, i2) -> i2.compareTo(i1))//Reverse Order
			.forEach(row -> removeRow(row));
	}
	
	public void removeSelectedRows(){
		removeRows(getSelectedRows());
	}
	
	public List<M> getSelectedRowsData() {
		int[] rows = getSelectedRows();
		if (rows.length == 0)
			return Collections.emptyList();

		List<M> data = new ArrayList<>();
		for (int row: rows)
			data.add(getModel().getModelList().get(convertRowIndexToModel(row)));
		
		return data;
	}
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if(getRowCount() == 0) {
	      Graphics2D g2d = (Graphics2D) g;
	      g2d.setColor(Color.BLACK);
	      g2d.drawString(noRowsMessage,10,20);
	    }
	  }
	
	@Override
	public MVPTableModel<M> getModel() {
		return (MVPTableModel<M>) super.getModel();
	}
}
