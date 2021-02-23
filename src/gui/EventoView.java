package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import myGuiLib.DatePanelGMA;
import myGuiLib.DurationPanelGOM;
import myGuiLib.TableQuoteAggiuntive;
import myGuiLib.TimePanelOM;
import social.RaccoltaEventi;

public class EventoView implements View {
	private static final String SUCCESS_EVENT_MESSAGE = "Evento inserito con successo";
	private static final Dimension DIMENSIONE_LABEL = new Dimension(250, 500);
	private static final Dimension DIMENSIONE_BUTTON = new Dimension(250, 500);
	private static final Dimension DIMENSIONE_INSERT = new Dimension(250, 500);
	private static final Dimension ELEMENT_SIZE = new Dimension(300, 22);
	private static final Dimension TWO_DIGIT = new Dimension(30, 20);
	private static final Dimension FOUR_DIGIT = new Dimension(60, 20);

	private JPanel mainPanel;
	private JPanel eventPanel;
	private JButton confirmButton;
	private JButton escButton;
	private JButton aggiungiButton;
	private JButton rimuoviButton;

	private JPanel dataInizioPanel;
	private JPanel orarioInizioPanel;

	private JPanel dataScadenzaPanel;
	private JPanel dataFinalePanel;
	private JPanel orarioFinalePanel;
	private JPanel dataRitiroPanel;
	private JPanel durataPanel;
	private JPanel insertQuotePanel;

	private JTextField textFieldLuogo;
	private JTextField textFieldQuota;

	private JTextField textMaxPartecipanti;
	private JTextField textFieldNome;
	private JTextField textFieldTolleranza;
	private JTextField textNote;
	private JTextField textCompreso;
	private JCheckBox checkInvito;

	private JComboBox<String> comboTipo;
	private JTextField nomeField;
	private JTextField cashField;
	private int numTipiEventi;

	/**
	 * @wbp.parser.entryPoint
	 */
	public EventoView(int numTipiEventi) {
		this.numTipiEventi = numTipiEventi;
		buildPanel();
	}

	@Override
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void buildPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		eventPanel = new JPanel();
		eventPanel.setLayout(new FlowLayout());
		eventPanel.add(labelPanel());
		eventPanel.add(insertPanel());
		eventPanel.add(buttonPanel());
		eventPanel.setVisible(true);

		mainPanel.add(eventPanel);
		mainPanel.add(quotePanel());
		mainPanel.setVisible(true);
	}

	public JPanel buttonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(DIMENSIONE_BUTTON);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		confirmButton = new JButton("Inserisci evento");
		confirmButton.setMaximumSize(ELEMENT_SIZE);
		buttonPanel.add(confirmButton);

		escButton = new JButton("Indietro");
		confirmButton.setMaximumSize(ELEMENT_SIZE);
		buttonPanel.add(escButton);

		return buttonPanel;
	}

	public JPanel insertPanel() {
		JPanel insertPanel = new JPanel();
		insertPanel.setPreferredSize(DIMENSIONE_INSERT);
		insertPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.Y_AXIS));

		comboTipo = showListaTipiEvento(numTipiEventi);
		comboTipo.setMaximumSize(ELEMENT_SIZE);
		insertPanel.add(comboTipo);

		dataInizioPanel = new DatePanelGMA(ELEMENT_SIZE, TWO_DIGIT, FOUR_DIGIT);
		insertPanel.add(dataInizioPanel);

		orarioInizioPanel = new TimePanelOM(ELEMENT_SIZE, TWO_DIGIT);
		insertPanel.add(orarioInizioPanel);

		textFieldLuogo = new JTextField();
		textFieldLuogo.setMaximumSize(ELEMENT_SIZE);
		insertPanel.add(textFieldLuogo);

		textFieldQuota = new JTextField();
		textFieldQuota.setMaximumSize(ELEMENT_SIZE);
		insertPanel.add(textFieldQuota);

		dataScadenzaPanel = new DatePanelGMA(ELEMENT_SIZE, TWO_DIGIT, FOUR_DIGIT);
		insertPanel.add(dataScadenzaPanel);

		textMaxPartecipanti = new JTextField();
		textMaxPartecipanti.setMaximumSize(ELEMENT_SIZE);
		insertPanel.add(textMaxPartecipanti);

		textFieldNome = new JTextField();
		textFieldNome.setMaximumSize(ELEMENT_SIZE);
		insertPanel.add(textFieldNome);

		textFieldTolleranza = new JTextField();
		textFieldTolleranza.setMaximumSize(ELEMENT_SIZE);
		insertPanel.add(textFieldTolleranza);

		dataFinalePanel = new DatePanelGMA(ELEMENT_SIZE, TWO_DIGIT, FOUR_DIGIT);
		insertPanel.add(dataFinalePanel);

		orarioFinalePanel = new TimePanelOM(ELEMENT_SIZE, TWO_DIGIT);
		insertPanel.add(orarioFinalePanel);

		dataRitiroPanel = new DatePanelGMA(ELEMENT_SIZE, TWO_DIGIT, FOUR_DIGIT);
		insertPanel.add(dataRitiroPanel);

		durataPanel = new DurationPanelGOM(ELEMENT_SIZE, TWO_DIGIT);
		insertPanel.add(durataPanel);

		textNote = new JTextField();
		textNote.setMaximumSize(ELEMENT_SIZE);
		insertPanel.add(textNote);

		textCompreso = new JTextField();
		textCompreso.setMaximumSize(ELEMENT_SIZE);
		insertPanel.add(textCompreso);

		checkInvito = new JCheckBox();
		insertPanel.add(checkInvito);
		checkInvito.setMaximumSize(ELEMENT_SIZE);

		return insertPanel;
	}

	public JPanel labelPanel() {
		JPanel labelPanel = new JPanel();
		labelPanel.setPreferredSize(DIMENSIONE_LABEL);
		labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

		JLabel lblTipo = new JLabel("*Tipo evento");
		lblTipo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblTipo);

		JLabel lblDataInizio = new JLabel("*Data inizio");
		lblDataInizio.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblDataInizio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblDataInizio);

		JLabel lblOrarioInizio = new JLabel("*Orario inizio");
		lblOrarioInizio.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblOrarioInizio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblOrarioInizio);

		JLabel lblLuogo = new JLabel("*Luogo");
		lblLuogo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblLuogo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblLuogo);

		JLabel lblQuota = new JLabel("*Quota (€)");
		lblQuota.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblQuota.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblQuota);

		JLabel lblScadenzaIscrizione = new JLabel("*Scadenza iscrizione");
		lblScadenzaIscrizione.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblScadenzaIscrizione.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblScadenzaIscrizione);

		JLabel lblMaxPartecipanti = new JLabel("*Partecipanti massimi");
		lblMaxPartecipanti.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblMaxPartecipanti.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblMaxPartecipanti);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblNome);

		JLabel lblTolleranze = new JLabel("Tolleranza partecipanti");
		lblTolleranze.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTolleranze.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblTolleranze);

		JLabel lblDataFinale = new JLabel("Data finale");
		lblDataFinale.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblDataFinale.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblDataFinale);

		JLabel lblOraFinale = new JLabel("Ora finale");
		lblOraFinale.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblOraFinale.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblOraFinale);

		JLabel lblTermineRitiro = new JLabel("Termine ritiro");
		lblTermineRitiro.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTermineRitiro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblTermineRitiro);

		JLabel lblDurata = new JLabel("Durata");
		lblDurata.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblDurata.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblDurata);

		JLabel lblNote = new JLabel("Note");
		lblNote.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblNote);

		JLabel lblCompresoQuota = new JLabel("Nella quota è compreso");
		lblCompresoQuota.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblCompresoQuota.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblCompresoQuota);

		JLabel lblInvitaInteressati = new JLabel("Devo invitare interessati");
		lblInvitaInteressati.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblInvitaInteressati.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPanel.add(lblInvitaInteressati);

		return labelPanel;
	}
	

	public JPanel quotePanel() {
		JPanel qPanel = new JPanel();
		qPanel.setLayout(new BoxLayout(qPanel, BoxLayout.X_AXIS));

		JPanel quoteLabelPanel = new JPanel();
		quoteLabelPanel.setPreferredSize(DIMENSIONE_LABEL);
		JLabel quoteLabel = new JLabel("Quote aggiuntive");
		quoteLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		quoteLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		quoteLabelPanel.add(quoteLabel);

		insertQuotePanel = new TableQuoteAggiuntive();
		
		JPanel buttonQuotePanel = new JPanel();
		buttonQuotePanel.setPreferredSize(DIMENSIONE_LABEL);

		aggiungiButton = new JButton("Aggiungi quota");
		rimuoviButton = new JButton("Rimuovi quota");
		buttonQuotePanel.add(aggiungiButton);
		buttonQuotePanel.add(rimuoviButton);

		qPanel.add(quoteLabelPanel);
		qPanel.add(insertQuotePanel);
		qPanel.add(buttonQuotePanel);
		return qPanel;
	}

	public JComboBox<String> showListaTipiEvento(int tot) {
		String opzioni[] = new String[tot];
		for (int i = 0; i < tot; i++) {
			opzioni[i] = RaccoltaEventi.TIPI_EVENTO[i];
		}
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(opzioni);

		JComboBox<String> box = new JComboBox<String>(comboModel);
		return box;
	}

	public JButton getConfirmButton() {
		return confirmButton;
	}

	public void setConfirmButton(JButton confirmButton) {
		this.confirmButton = confirmButton;
	}

	public JButton getEscButton() {
		return escButton;
	}

	public void setEscButton(JButton escButton) {
		this.escButton = escButton;
	}

	public JTextField getTextFieldLuogo() {
		return textFieldLuogo;
	}

	public void setTextFieldLuogo(JTextField textFieldLuogo) {
		this.textFieldLuogo = textFieldLuogo;
	}

	public JTextField getTextFieldQuota() {
		return textFieldQuota;
	}

	public void setTextFieldQuota(JTextField textFieldQuota) {
		this.textFieldQuota = textFieldQuota;
	}

	public JTextField getTextMaxPartecipanti() {
		return textMaxPartecipanti;
	}

	public void setTextMaxPartecipanti(JTextField textMaxPartecipanti) {
		this.textMaxPartecipanti = textMaxPartecipanti;
	}

	public JTextField getTextFieldNome() {
		return textFieldNome;
	}

	public void setTextFieldNome(JTextField textFieldNome) {
		this.textFieldNome = textFieldNome;
	}

	public JTextField getTextFieldTolleranza() {
		return textFieldTolleranza;
	}

	public void setTextFieldTolleranza(JTextField textFieldTolleranza) {
		this.textFieldTolleranza = textFieldTolleranza;
	}

	public JTextField getTextNote() {
		return textNote;
	}

	public void setTextNote(JTextField textNote) {
		this.textNote = textNote;
	}

	public JTextField getTextCompreso() {
		return textCompreso;
	}

	public void setTextCompreso(JTextField textCompreso) {
		this.textCompreso = textCompreso;
	}

	public JCheckBox getCheckInvito() {
		return checkInvito;
	}

	public void setCheckInvito(JCheckBox checkInvito) {
		this.checkInvito = checkInvito;
	}

	public JTextField getNomeField() {
		return nomeField;
	}

	public void setNomeField(JTextField nomeField) {
		this.nomeField = nomeField;
	}

	public JTextField getCashField() {
		return cashField;
	}

	public void setCashField(JTextField cashField) {
		this.cashField = cashField;
	}

	public JButton getAggiungiButton() {
		return aggiungiButton;
	}

	public void setAggiungiButton(JButton aggiungiButton) {
		this.aggiungiButton = aggiungiButton;
	}

	public JComboBox<String> getComboTipo() {
		return comboTipo;
	}

	public void setComboTipo(JComboBox<String> comboTipo) {
		this.comboTipo = comboTipo;
	}

	public JPanel getDataInizioPanel() {
		return dataInizioPanel;
	}

	public void setDataInizioPanel(JPanel dataInizioPanel) {
		this.dataInizioPanel = dataInizioPanel;
	}

	public JPanel getOrarioInizioPanel() {
		return orarioInizioPanel;
	}

	public void setOrarioInizioPanel(JPanel orarioInizioPanel) {
		this.orarioInizioPanel = orarioInizioPanel;
	}

	public JPanel getDataScadenzaPanel() {
		return dataScadenzaPanel;
	}

	public void setDataScadenzaPanel(JPanel dataScadenzaPanel) {
		this.dataScadenzaPanel = dataScadenzaPanel;
	}

	public JPanel getDataFinalePanel() {
		return dataFinalePanel;
	}

	public void setDataFinalePanel(JPanel dataFinalePanel) {
		this.dataFinalePanel = dataFinalePanel;
	}

	public JPanel getOrarioFinalePanel() {
		return orarioFinalePanel;
	}

	public void setOrarioFinalePanel(JPanel orarioFinalePanel) {
		this.orarioFinalePanel = orarioFinalePanel;
	}

	public JPanel getDataRitiroPanel() {
		return dataRitiroPanel;
	}

	public void setDataRitiroPanel(JPanel dataRitiroPanel) {
		this.dataRitiroPanel = dataRitiroPanel;
	}

	public JPanel getDurataPanel() {
		return durataPanel;
	}

	public void setDurataPanel(JPanel durataPanel) {
		this.durataPanel = durataPanel;
	}

	public JPanel getInsertQuotePanel() {
		return insertQuotePanel;
	}

	public void setInsertQuotePanel(JPanel insertQuotePanel) {
		this.insertQuotePanel = insertQuotePanel;
	}

	public JButton getRimuoviButton() {
		return rimuoviButton;
	}

	public void setRimuoviButton(JButton rimuoviButton) {
		this.rimuoviButton = rimuoviButton;
	}

	public void obligatoryInputErrorMessage() {
		JOptionPane.showMessageDialog(null, INPUT_OBLIGATORY_ERROR_MESSAGE, WARNING, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void inputErrorMessage() {
		JOptionPane.showMessageDialog(null, INPUT_ERROR_MESSAGE, WARNING, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void successMessage() {
		JOptionPane.showMessageDialog(null, SUCCESS_EVENT_MESSAGE, WARNING, JOptionPane.INFORMATION_MESSAGE);
	}

}
