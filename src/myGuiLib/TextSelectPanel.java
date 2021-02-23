package myGuiLib;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class TextSelectPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIM_TEXT = new Dimension(400, 600);
	private static final Dimension DIM_INSERT = new Dimension(400, 100);

	private String text;
	private int numberSelection;
	private String labelDescrizione;
	private String labelButton;
	private JPanel textPanel;
	private JPanel selectPanel;
	private JPanel comboPanel;
	private JButton btnSelect;
	private JComboBox<Integer> combo;
	private JLabel lblSelect;
	private GridBagConstraints textCns;
	private GridBagConstraints selectCns;

	/**
	 * @wbp.parser.entryPoint
	 */
	public TextSelectPanel(String text, int numberSelection, String labelDescrizione, String labelButton) {
		this.text = text;
		this.numberSelection = numberSelection;
		this.setLabelDescrizione(labelDescrizione);
		this.setLabelButton(labelButton);

		// testo
		textPanel = new JPanel();
		textPanel.setPreferredSize(DIM_TEXT);
		textCns = new GridBagConstraints();
		textCns.weighty = 3.0;
		textCns.weightx = 1.0;
		textCns.fill = GridBagConstraints.BOTH;
		textCns.gridx = 0;
		textCns.gridy = 0;
		refreshText();

		// combo
		selectPanel = new JPanel();
		selectPanel.setPreferredSize(DIM_INSERT);
		selectCns = new GridBagConstraints();
		selectCns.gridwidth = 2;
		selectCns.anchor = GridBagConstraints.SOUTH;
		selectCns.weighty = 0.5;
		selectCns.weightx = 1.0;
		selectCns.gridx = 0;
		selectCns.gridy = 1;
		selectCns.fill = GridBagConstraints.BOTH;
		selectPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		selectPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));
		lblSelect = new JLabel(labelDescrizione);
		lblSelect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		selectPanel.add(lblSelect);
		comboPanel = new JPanel();
		refreshCount();
		selectPanel.add(comboPanel);

		// button
		btnSelect = new JButton(labelButton);
		btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 19));

		this.add(textPanel, textCns);
		this.add(selectPanel);
		this.add(btnSelect);

	}

	public void refresh() {
		refreshText();
		refreshCount();
		revalidate();
		repaint();
	}

	public void refreshText() {
		textPanel.removeAll();
		textPanel.add(showText(text));
	}

	public void refreshCount() {
		comboPanel.removeAll();
		combo = showListaNumeri(numberSelection);
		comboPanel.add(combo);
	}

	public JComboBox<Integer> showListaNumeri(int tot) {
		Integer opzioni[] = new Integer[tot];
		for (int i = 0; i < tot; i++) {
			opzioni[i] = i + 1;
		}
		DefaultComboBoxModel<Integer> comboModel = new DefaultComboBoxModel<Integer>(opzioni);

		JComboBox<Integer> box = new JComboBox<Integer>(comboModel);
		return box;
	}

	public JScrollPane showText(String text) {
		JTextArea textArea = new JTextArea(text, 35, 35);
		textArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setPreferredSize(DIM_TEXT);

		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		return scroll;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getNumberSelection() {
		return numberSelection;
	}

	public void setNumberSelection(int numberSelection) {
		this.numberSelection = numberSelection;
	}

	public JPanel getTextPanel() {
		return textPanel;
	}

	public void setTextPanel(JPanel textPanel) {
		this.textPanel = textPanel;
	}

	public JPanel getSelectPanel() {
		return selectPanel;
	}

	public void setSelectPanel(JPanel selectPanel) {
		this.selectPanel = selectPanel;
	}

	public JPanel getComboPanel() {
		return comboPanel;
	}

	public void setComboPanel(JPanel comboPanel) {
		this.comboPanel = comboPanel;
	}

	public JComboBox<Integer> getCombo() {
		return combo;
	}

	public void setCombo(JComboBox<Integer> combo) {
		this.combo = combo;
	}
	
	public JButton getBtnSelect() {
		return btnSelect;
	}

	public void setBtnSelect(JButton btnSelect) {
		this.btnSelect = btnSelect;
	}

	public String getLabelButton() {
		return labelButton;
	}

	public void setLabelButton(String labelButton) {
		this.labelButton = labelButton;
	}

	public String getLabelDescrizione() {
		return labelDescrizione;
	}

	public void setLabelDescrizione(String labelDescrizione) {
		this.labelDescrizione = labelDescrizione;
	}

}
