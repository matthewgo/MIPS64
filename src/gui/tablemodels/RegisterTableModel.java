package gui.tablemodels;

import java.math.BigInteger;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.StringUtils;

import gui.MainFrame;

public class RegisterTableModel extends AbstractTableModel {

	String[][] table;

	public RegisterTableModel(String[][] registers) {
		super();
		table = registers;
	}

	@Override
	public int getRowCount() {
		return table.length;
	}

	@Override
	public int getColumnCount() {
		return table[0].length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return table[rowIndex][columnIndex];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 1 || col == 3)
			return true;
		return false;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		String val = (String) value;
		if (row == 0)
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Registers R0 and F0 can not be edited.");
		else if (val.length() <= 16 && col == 1) {
			val = StringUtils.leftPad(val, 16, "0");
			try {
				new BigInteger(val, 16);
				table[row][col] = val;
				this.fireTableCellUpdated(row, col);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(MainFrame.getInstance(), val + " is an invalid register value!");
			}
		} else if (val.length() > 16 && col == 1)
			JOptionPane.showMessageDialog(MainFrame.getInstance(), val + " is an invalid register value!");
		else if (col == 3) {
			try {
				Double.parseDouble(val);
				table[row][col] = val;
				this.fireTableCellUpdated(row, col);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(MainFrame.getInstance(), val + " is an invalid floating point value!");
			}
		}
	}
}
