package gui;


import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SelectQuoteView implements View {
	private static String[] header = { "Nome", "Importo", "Seleziona" };
	private static Dimension DIMENSION_FRAME = new Dimension(300, 300);

	private HashMap<String, Double> quote;
	private JPanel mainPanel;
	private JPanel quotePanel;

	private DefaultTableModel dtm = new DefaultTableModel(null, header) {
		private static final long serialVersionUID = 1L;
		public Class<?> getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
	};

	private JTable quoteTable = new JTable(dtm);
	private JScrollPane scrollPane = new JScrollPane(quoteTable);
	private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
	private boolean isAutoScroll;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public SelectQuoteView(HashMap<String, Double> quote) {
		this.quote = quote;
		buildPanel();
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void buildPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout());
		mainPanel.setPreferredSize(DIMENSION_FRAME);
		
		quotePanel = new JPanel();
		quotePanel.setLayout(new BoxLayout(quotePanel, BoxLayout.Y_AXIS));
		Dimension dimensionQuote = new Dimension(200, quoteTable.getRowHeight() * quote.size());
		fillTable();
		quoteTable.setPreferredScrollableViewportSize(dimensionQuote);
		quotePanel.setPreferredSize(dimensionQuote);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		vScroll.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				setAutoScroll(!e.getValueIsAdjusting());
			}
		});
		quotePanel.add(scrollPane);
		mainPanel.add(quotePanel);
	}
	
	public void fillTable() {
		Set<String> keySet = quote.keySet();
		for(String key:keySet){
			Double value = quote.get(key);
			//JCheckBox aCheck = new JCheckBox();
			dtm.addRow(new Object[] {key, value, false});
		}
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
