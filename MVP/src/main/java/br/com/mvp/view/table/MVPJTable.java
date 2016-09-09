package br.com.mvp.view.table;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.TableRowSorter;

import br.com.mvp.view.table.mapper.ColumnValueResolver;
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
		
		return getModel().getModelList().get(row);
	}
	
	public List<M> getSelectedRowsData() {
		int[] rows = getSelectedRows();
		if (rows.length == 0)
			return Collections.emptyList();

		List<M> data = new ArrayList<>();
		for (int row: rows)
			data.add(getModel().getModelList().get(row));
		
		return data;
	}
	
	@Override
	public void sorterChanged(RowSorterEvent e) {
		super.sorterChanged(e);
		
		TableRowSorter<MVPTableModel<M>> rowSorter = (TableRowSorter<MVPTableModel<M>>) e.getSource();
		MVPTableModel<M> model = rowSorter.getModel();
		
		if (!rowSorter.getSortKeys().isEmpty()){
			SortKey sortKey = rowSorter.getSortKeys().get(0);
			List<M> modelList = model.getModelList();
			
			TableMapper<M> mapper = model.getMapper();
			ColumnValueResolver<M> valueResolver = mapper
					.getColumnMapper(sortKey.getColumn())
					.getValueResolver();
			
			Comparator<M> c = (m1, m2) -> {
				switch (sortKey.getSortOrder()) {
				case ASCENDING:
					return ((Comparable<Object>)valueResolver.getColumnValue(m1)).compareTo( 
							(Comparable<Object>)valueResolver.getColumnValue(m2)
							);
				case DESCENDING:
					return ((Comparable<Object>)valueResolver.getColumnValue(m2)).compareTo( 
						(Comparable<Object>)valueResolver.getColumnValue(m1)
						);
				default:
					return 0;
				}
			};
			
			modelList.sort(c);
		}
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
