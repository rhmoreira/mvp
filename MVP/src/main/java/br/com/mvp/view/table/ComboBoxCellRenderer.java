package br.com.mvp.view.table;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComboBoxCellRenderer implements TableCellRenderer {
	
	private JComboBox<?> comboBox;
	
	public ComboBoxCellRenderer(JComboBox<?> comboBox) {
		this.comboBox = comboBox;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			comboBox.setForeground(table.getSelectionForeground());
			comboBox.setBackground(table.getSelectionBackground());
		} else {
			comboBox.setForeground(table.getForeground());
			comboBox.setBackground(table.getBackground());
		}
		comboBox.setSelectedItem(value);
		return comboBox;
	}
}
