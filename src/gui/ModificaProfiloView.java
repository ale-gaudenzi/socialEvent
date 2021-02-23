package gui;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.JTextField;

public class ModificaProfiloView implements View {
	private static final Dimension dimensionePanel = new Dimension(250, 250);

	private static final String SUCCESSO_MESSAGE = "Salvato con successo";
	private static final String IMPOSSIBILE_MESSAGE = "Non puoi avere un'età minima maggiore di quella massima!";

	private JTextField textEtaMin;
	private JTextField textEtaMax;
	private JPanel mainPanel;
	private GridBagLayout gbl_mainPanel;
	private JPanel buttonPanel;
	private BoxLayout boxLayout;
	private GridBagConstraints buttonCns;
	private JButton btnIndietro;
	private JButton btnSalva;
	private JPanel insertPanel;
	private GridBagConstraints insertCns;
	private JPanel labelPanel;
	private GridBagConstraints labelCns;
	private JLabel lblEtaMinima;
	private JLabel lblEtaMassima;

	/**
	 * @wbp.parser.entryPoint
	 */
	public ModificaProfiloView() {
		buildPanel();
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void buildPanel() {
		mainPanel = new JPanel();
		gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWeights = new double[] { 1.0, 0.0, 0.0 };
		mainPanel.setLayout(gbl_mainPanel);

		// LABEL UP
		labelPanel = new JPanel();
		labelPanel.setPreferredSize(dimensionePanel);
		labelCns = new GridBagConstraints();
		labelCns.insets = new Insets(10, 0, 5, 5);
		labelCns.weighty = 3.0;
		labelCns.weightx = 1.0;
		labelCns.fill = GridBagConstraints.BOTH;
		labelCns.gridx = 0;
		labelCns.gridy = 0;
		mainPanel.add(labelPanel, labelCns);
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		lblEtaMinima = new JLabel("Nuova et\u00E0 minima");
		lblEtaMinima.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblEtaMinima.setFont(new Font("Tahoma", Font.PLAIN, 19));
		labelPanel.add(lblEtaMinima);
		lblEtaMassima = new JLabel("Nuova et\u00E0 massima");
		lblEtaMassima.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblEtaMassima.setFont(new Font("Tahoma", Font.PLAIN, 19));
		labelPanel.add(lblEtaMassima);

		// INSERT UP
		insertPanel = new JPanel();
		insertPanel.setPreferredSize(dimensionePanel);
		insertCns = new GridBagConstraints();
		insertCns.insets = new Insets(5, 0, 5, 5);
		insertCns.weighty = 3.0;
		insertCns.weightx = 1.0;
		insertCns.fill = GridBagConstraints.BOTH;
		insertCns.gridx = 1;
		insertCns.gridy = 0;
		mainPanel.add(insertPanel, insertCns);
		insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.Y_AXIS));

		textEtaMin = new JTextField();
		textEtaMin.setAlignmentY(Component.TOP_ALIGNMENT);
		insertPanel.add(textEtaMin);
		textEtaMin.setColumns(10);
		textEtaMin.setMaximumSize(new Dimension(300, 28));
		textEtaMax = new JTextField();
		textEtaMax.setAlignmentY(Component.TOP_ALIGNMENT);
		insertPanel.add(textEtaMax);
		textEtaMax.setColumns(10);
		textEtaMax.setMaximumSize(new Dimension(300, 28));

		// BUTTON
		buttonPanel = new JPanel();
		boxLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
		buttonPanel.setLayout(boxLayout);
		mainPanel.add(buttonPanel);
		buttonPanel.setPreferredSize(dimensionePanel);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		buttonCns = new GridBagConstraints();
		buttonCns.insets = new Insets(0, 0, 5, 0);
		buttonCns.fill = GridBagConstraints.BOTH;
		buttonCns.weighty = 3.0;
		buttonCns.weightx = 0.5;
		buttonCns.gridx = 2;
		buttonCns.gridy = 0;
		mainPanel.add(buttonPanel, buttonCns);

		btnSalva = new JButton("Salva");
		btnSalva.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonPanel.add(btnSalva);

		btnIndietro = new JButton("Indietro");
		buttonPanel.add(btnIndietro);
		btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 19));

	}

	public JComboBox<Integer> showListaNumeri(int tot) {
		Integer opzioni[] = new Integer[tot];
		for (int i = 0; i < tot; i++) {
			opzioni[i] = i + 1;
		}
		DefaultComboBoxModel<Integer> comboModel = new DefaultComboBoxModel<Integer>(opzioni);
		return new JComboBox<Integer>(comboModel);
	}

	public JScrollPane showText(String bacheca) {
		JTextArea textArea = new JTextArea(bacheca, 35, 35);
		textArea.setPreferredSize(dimensionePanel);
		textArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		return scroll;
	}

	public JTextField getTextEtaMin() {
		return textEtaMin;
	}

	public void setTextEtaMin(JTextField textEtaMin) {
		this.textEtaMin = textEtaMin;
	}

	public JTextField getTextEtaMax() {
		return textEtaMax;
	}

	public void setTextEtaMax(JTextField textEtaMax) {
		this.textEtaMax = textEtaMax;
	}

	public JButton getBtnIndietro() {
		return btnIndietro;
	}

	public void setBtnIndietro(JButton btnIndietro) {
		this.btnIndietro = btnIndietro;
	}

	public JButton getBtnSalva() {
		return btnSalva;
	}

	public void setBtnSalva(JButton btnSalva) {
		this.btnSalva = btnSalva;
	}
	
	public void inputErrorMessage() {
		JOptionPane.showMessageDialog(null, INPUT_ERROR_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void successoMessage() {
		JOptionPane.showMessageDialog(null, SUCCESSO_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void etaImpossibileErrorMessage() {
		JOptionPane.showMessageDialog(null, IMPOSSIBILE_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}

}
