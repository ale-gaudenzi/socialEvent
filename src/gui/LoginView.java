package gui;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Font;

public class LoginView implements View {	
	private static final String SUCCESS_RESET_MESSAGE = "Reset effettuato con successo";
	
	private JPanel mainPanel;
	private JPanel labelPanel;
	private JLabel lblName;
	private JLabel lblPassword;
	private JPanel insertPanel;
	private JTextField textFieldName;
	private JTextField textFieldPassword;
	private JPanel buttonPanel;
	private JButton btnAccedi;
	private JButton btnRegistrati;
	private JButton btnReset;
	
	public LoginView() {
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

		// INSERT
		insertPanel = new JPanel();
		insertPanel.setPreferredSize(new Dimension(250, 700));
		insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.Y_AXIS));
		insertPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
		mainPanel.add(insertPanel);

		textFieldName = new JTextField();
		insertPanel.add(textFieldName);
		textFieldName.setMaximumSize(new Dimension(300, 30));

		textFieldPassword = new JTextField();
		insertPanel.add(textFieldPassword);
		textFieldPassword.setMaximumSize(new Dimension(300, 30));

		insertPanel.add(textFieldName);
		insertPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		insertPanel.add(textFieldPassword);

		// BUTTON
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(250, 700));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPanel.add(buttonPanel);
		
		btnAccedi = new JButton("Accedi");
		btnAccedi.setAlignmentY(Component.TOP_ALIGNMENT);
		btnAccedi.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnAccedi.setPreferredSize(new Dimension(100, 30));
		buttonPanel.add(btnAccedi);
		
		btnRegistrati = new JButton("Registrati");
		btnRegistrati.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonPanel.add(btnRegistrati);
		
		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonPanel.add(btnReset);	
	}

	public void utenteNonTrovatoErrorMessage() {
		JOptionPane.showMessageDialog(null, USER_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void inputErrorMessage() {
		JOptionPane.showMessageDialog(null, INPUT_ERROR_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void successoResetMessage() {
		JOptionPane.showMessageDialog(null, SUCCESS_RESET_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
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

	public JButton getBtnAccedi() {
		return btnAccedi;
	}

	public void setBtnAccedi(JButton btnAccedi) {
		this.btnAccedi = btnAccedi;
	}

	public JButton getBtnRegistrati() {
		return btnRegistrati;
	}

	public void setBtnRegistrati(JButton btnRegistrati) {
		this.btnRegistrati = btnRegistrati;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public void setBtnReset(JButton btnReset) {
		this.btnReset = btnReset;
	}
}
