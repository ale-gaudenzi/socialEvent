package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class RegistrationView implements View {
	private JPanel mainPanel;
	private JPanel labelPanel;
	private JLabel lblName;
	private JLabel lblPassword;
	private JLabel lblNick;
	private JLabel lblEtaMin;
	private JLabel lblEtaMax;
	private JLabel lblInteressi;
	private	JPanel buttonPanel;
	private JButton btnRegistrati;
	private JButton btnEsci;
	private JPanel insertPanel;
	private JTextField textFieldName;
	private JTextField textFieldPassword;
	private JTextField textFieldNick;
	private JTextField textFieldEtaMin;
	private JTextField textFieldEtaMax;
	private JPanel categoryPanel;
	private ArrayList<JCheckBox> checkBoxInteressi = new ArrayList<JCheckBox>();
	private String[] listaEventi;


	private static final String SUCCESS_MESSAGE = "Utente iscritto con successo";
	private static final String NOME_PRESENTE_ERROR_MESSAGE = "Nome utente già presente nel sistema!";
	
	public RegistrationView(String[] tipiEvento) {
		this.listaEventi = tipiEvento;
		buildPanel();
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void buildPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 3, 0, 0));

		// LABEL
		labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(250,700));
		labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		labelPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		mainPanel.add(labelPanel);

		lblName = new JLabel("Nome: ");
		lblName.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelPanel.add(lblName);

		lblPassword = new JLabel("Password: ");
		lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelPanel.add(lblPassword);

		lblNick = new JLabel("Nick: ");
		lblNick.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNick.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelPanel.add(lblNick);

		lblEtaMin = new JLabel("Età minima: ");
		lblEtaMin.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblEtaMin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelPanel.add(lblEtaMin);

		lblEtaMax = new JLabel("Età massima: ");
		lblEtaMax.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblEtaMax.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelPanel.add(lblEtaMax);

		lblInteressi = new JLabel("Categorie d'interesse: ");
		lblInteressi.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblInteressi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelPanel.add(lblInteressi);

		// INSERT
		insertPanel = new JPanel();
		insertPanel.setPreferredSize(new Dimension(250, 700));
		insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.Y_AXIS));
		insertPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
		mainPanel.add(insertPanel);

		textFieldName = new JTextField();
		textFieldName.setColumns(1);
		textFieldName.setPreferredSize(new Dimension(300, 30));
		textFieldName.setMaximumSize(new Dimension(300, 30));
		textFieldName.setMinimumSize(new Dimension(100, 25));

		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(1);
		textFieldPassword.setPreferredSize(new Dimension(300, 30));
		textFieldPassword.setMaximumSize(new Dimension(300, 30));
		textFieldPassword.setMinimumSize(new Dimension(100, 25));

		textFieldNick = new JTextField();
		textFieldNick.setColumns(1);
		textFieldNick.setPreferredSize(new Dimension(300, 30));
		textFieldNick.setMaximumSize(new Dimension(300, 30));
		textFieldNick.setMinimumSize(new Dimension(100, 25));

		textFieldEtaMin = new JTextField();
		textFieldEtaMin.setColumns(1);
		textFieldEtaMin.setPreferredSize(new Dimension(300, 30));
		textFieldEtaMin.setMaximumSize(new Dimension(300, 30));
		textFieldEtaMin.setMinimumSize(new Dimension(100, 25));

		textFieldEtaMax = new JTextField();
		textFieldEtaMax.setColumns(1);
		textFieldEtaMax.setPreferredSize(new Dimension(300, 30));
		textFieldEtaMax.setMaximumSize(new Dimension(300, 30));
		textFieldEtaMax.setMinimumSize(new Dimension(100, 25));

		categoryPanel = checkBox(listaEventi); 
		insertPanel.add(textFieldName);
		insertPanel.add(textFieldPassword);
		insertPanel.add(textFieldNick);
		insertPanel.add(textFieldEtaMin);
		insertPanel.add(textFieldEtaMax);
		insertPanel.add(categoryPanel);

		insertPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		// BUTTON+LABEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(250, 700));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

		mainPanel.add(buttonPanel);

		btnRegistrati = new JButton("Registrati");
		btnRegistrati.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonPanel.add(btnRegistrati);

		btnEsci = new JButton("Indietro");
		btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonPanel.add(btnEsci);
	}

	public JPanel checkBox(String[] optionList) {
		JPanel panel = new JPanel();
		GridLayout panelLayout = new GridLayout(optionList.length, 1);
		panelLayout.setVgap(5);
		panel.setLayout(panelLayout);
		panel.setPreferredSize(new Dimension(300, 30));

		for (int i = 0; i < optionList.length; i++) {
			JCheckBox toAdd = new JCheckBox(optionList[i]);
			checkBoxInteressi.add(toAdd);
			panel.add(toAdd);
		}

		return panel;
	}

	public JButton getBtnRegistrati() {
		return btnRegistrati;
	}

	public void setBtnRegistrati(JButton btnRegistrati) {
		this.btnRegistrati = btnRegistrati;
	}

	public JButton getBtnEsci() {
		return btnEsci;
	}

	public void setBtnEsci(JButton btnEsci) {
		this.btnEsci = btnEsci;
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	public JTextField getTextFieldPassword() {
		return textFieldPassword;
	}

	public void setTextFieldPassword(JTextField textFieldPassword) {
		this.textFieldPassword = textFieldPassword;
	}

	public JTextField getTextFieldNick() {
		return textFieldNick;
	}

	public void setTextFieldNick(JTextField textFieldNick) {
		this.textFieldNick = textFieldNick;
	}

	public JTextField getTextFieldEtaMin() {
		return textFieldEtaMin;
	}

	public void setTextFieldEtaMin(JTextField textFieldEtaMin) {
		this.textFieldEtaMin = textFieldEtaMin;
	}

	public JTextField getTextFieldEtaMax() {
		return textFieldEtaMax;
	}

	public void setTextFieldEtaMax(JTextField textFieldEtaMax) {
		this.textFieldEtaMax = textFieldEtaMax;
	}

	public ArrayList<JCheckBox> getCheckBoxInteressi() {
		return checkBoxInteressi;
	}

	public void setCheckBoxInteressi(ArrayList<JCheckBox> checkBoxInteressi) {
		this.checkBoxInteressi = checkBoxInteressi;
	}	
	
	public void inputErrorMessage() {
		JOptionPane.showMessageDialog(null, INPUT_ERROR_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void obligatoryInputErrorMessage() {
		JOptionPane.showMessageDialog(null, INPUT_OBLIGATORY_ERROR_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void successRegistrationMessage() {
		JOptionPane.showMessageDialog(null, SUCCESS_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void nomeUtentePresenteMessage() {
		JOptionPane.showMessageDialog(null, NOME_PRESENTE_ERROR_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}
	

	public String[] getListaEventi() {
		return listaEventi;
	}

	public void setListaEventi(String[] listaEventi) {
		this.listaEventi = listaEventi;
	}
}
