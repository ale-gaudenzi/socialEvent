package myGuiLib;

import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableQuoteAggiuntive extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_ROWS = 0;
	private static String[] header = { "Nome", "Importo" };

	private DefaultTableModel dtm = new DefaultTableModel(null, header) {
		private static final long serialVersionUID = 1L;
		public Class<?> getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
	};

	private JTable table = new JTable(dtm);
	private JScrollPane scrollPane = new JScrollPane(table);
	private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
	private boolean isAutoScroll;

	public TableQuoteAggiuntive() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Dimension d = new Dimension(200, DEFAULT_ROWS * table.getRowHeight());
		table.setPreferredScrollableViewportSize(d);
		for (int i = 0; i < DEFAULT_ROWS; i++) {
			addRow();
		}
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		vScroll.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				setAutoScroll(!e.getValueIsAdjusting());
			}
		});
		this.add(scrollPane);
	}

	public void addRow() {
		dtm.addRow(new Object[] { "", "" });
	}
	
	public void removeRow() {
		if(dtm.getRowCount() != 0) {
			dtm.removeRow(dtm.getRowCount() - 1);
		}
	}

	public boolean isCellEditable(int row, int cols)
	{
		if (row == 0) {
			return false;
		}
		return true;
	}
	
	public DefaultTableModel getDtm() {
		return dtm;
	}

	public boolean isAutoScroll() {
		return isAutoScroll;
	}

	public void setAutoScroll(boolean isAutoScroll) {
		this.isAutoScroll = isAutoScroll;
	}

}