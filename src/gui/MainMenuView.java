package gui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import myGuiLib.TextSelectPanel;
import social.RaccoltaEventi;
import utility.ToStringUtil;

public class MainMenuView implements View, Observer {
	private static final Dimension DIM_BACHECA = new Dimension(400, 600);
	private static final Dimension DIM_BUTTON = new Dimension(200, 400);
	private static final String UTENTE_PARTECIPA_MESSAGE = "Stai già partecipando a questo evento!";
	private static final String SUCCESSO_ISCRIZIONE = "Utente iscritto con successo";
	private static final String FALLIMENTO_ISCRIZIONE = "Sei già iscritto all'evento";

	private int numeroEventi;
	private String bacheca;
	private JPanel mainPanel;
	private GridBagLayout gbl_mainPanel;
	private TextSelectPanel bachecaPanel;
	public TextSelectPanel getBachecaPanel() {
		return bachecaPanel;
	}

	private GridBagConstraints bachecaCns;
	private JPanel buttonPanel;
	private BoxLayout buttonBoxLayout;
	private GridBagConstraints buttonCns;
	private JButton btnSpaziopersonale;
	private JButton btnCreaEvento;
	private JButton btnEsci;
	private JComboBox<Integer> combo;
	private JButton btnPartecipa;

	/**
	 * @wbp.parser.entryPoint
	 */
	public MainMenuView(String bacheca, int numeroEventi) {
		this.bacheca = bacheca;
		this.numeroEventi = numeroEventi;
		buildPanel();
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void buildPanel() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.PINK);
		gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.rowWeights = new double[]{1.0};
		gbl_mainPanel.columnWeights = new double[] { 1.0, 0.0 };
		mainPanel.setLayout(gbl_mainPanel);

		// BACHECA
		bachecaPanel = new TextSelectPanel(bacheca, numeroEventi, "partecipa a:", "Partecipa");
		bachecaPanel.setPreferredSize(DIM_BACHECA);
		bachecaCns = new GridBagConstraints();
		bachecaCns.weighty = 3.0;
		bachecaCns.weightx = 1.0;
		bachecaCns.fill = GridBagConstraints.BOTH;
		bachecaCns.gridx = 0;
		bachecaCns.gridy = 0;
		mainPanel.add(bachecaPanel, bachecaCns);
		
		// BUTTON
		buttonPanel = new JPanel();
		buttonBoxLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
		buttonPanel.setLayout(buttonBoxLayout);
		mainPanel.add(buttonPanel);
		buttonPanel.setPreferredSize(DIM_BUTTON);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonCns = new GridBagConstraints();
		buttonCns.fill = GridBagConstraints.BOTH;
		buttonCns.weighty = 3.0;
		buttonCns.weightx = 0.5;
		buttonCns.gridx = 1;
		buttonCns.gridy = 0;
		mainPanel.add(buttonPanel, buttonCns);
		btnSpaziopersonale = new JButton("SpazioPersonale");
		buttonPanel.add(btnSpaziopersonale);
		btnSpaziopersonale.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCreaEvento = new JButton("Crea Evento");
		buttonPanel.add(btnCreaEvento);
		btnCreaEvento.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnEsci = new JButton("Esci");
		buttonPanel.add(btnEsci);
		btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 19));

		// INSERT
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		RaccoltaEventi raccolta = (RaccoltaEventi) o;
		bacheca = ToStringUtil.toStringRaccoltaEventi(raccolta);
		numeroEventi = raccolta.getRaccoltaE().size();
		
		bachecaPanel.setText(bacheca);
		bachecaPanel.setNumberSelection(numeroEventi);
		bachecaPanel.refresh();
	}
	
	//GETTER SETTER
	public JButton getBtnSpaziopersonale() {
		return btnSpaziopersonale;
	}

	public void setBtnSpaziopersonale(JButton btnSpazioPersonale) {
		this.btnSpaziopersonale = btnSpazioPersonale;
	}

	public JButton getBtnCreaEvento() {
		return btnCreaEvento;
	}

	public void setBtnCreaEvento(JButton btnCreaEvento) {
		this.btnCreaEvento = btnCreaEvento;
	}

	public JButton getBtnEsci() {
		return btnEsci;
	}

	public void setBtnEsci(JButton btnEsci) {
		this.btnEsci = btnEsci;
	}

	public JComboBox<Integer> getCombo() {
		return combo;
	}

	public void setCombo(JComboBox<Integer> combo) {
		this.combo = combo;
	}

	public JButton getBtnPartecipa() {
		return btnPartecipa;
	}

	public void setBtnPartecipa(JButton btnPartecipa) {
		this.btnPartecipa = btnPartecipa;
	}
	
	
	//DIAG MESSAGE
	public void utenteGiaPartecipaErrorMessage() {
		JOptionPane.showMessageDialog(null, UTENTE_PARTECIPA_MESSAGE, WARNING, JOptionPane.INFORMATION_MESSAGE);
	}

	public void fallimentoIscrizioneMessage() {
		JOptionPane.showMessageDialog(null, FALLIMENTO_ISCRIZIONE, WARNING, JOptionPane.INFORMATION_MESSAGE);
	}

	public void successoIscrizioneMessage() {
		JOptionPane.showMessageDialog(null, SUCCESSO_ISCRIZIONE, WARNING, JOptionPane.INFORMATION_MESSAGE);
	}
}
