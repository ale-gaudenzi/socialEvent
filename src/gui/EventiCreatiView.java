package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import controller.MasterController;
import myGuiLib.TextSelectPanel;
import social.SpazioPersonale;
import utility.ToStringUtil;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JOptionPane;

public class EventiCreatiView implements View, Observer {
	private static final Dimension DIM_TEXT = new Dimension(400, 600);
	private static final Dimension DIM_BUTTON = new Dimension(200, 400);
	private static final String SUCCESSO_RITIRO_MESSAGE = "Evento ritirato con successo";
	private static final String NESSUN_EVENTO_MESSAGE = "Non ci sono eventi che hai creato";

	private int numeroEventiCreati;
	private String eventiCreati;
	private JPanel mainPanel;
	private GridBagLayout gbl_mainPanel;
	private TextSelectPanel eventiPanel;
	private GridBagConstraints eventiCns;
	private JPanel buttonPanel;
	private BoxLayout boxLayout;
	private GridBagConstraints buttonCns;
	private JButton btnEsci;

	/**
	 * @wbp.parser.entryPoint
	 */
	public EventiCreatiView(String eventiCreati, int numeroEventiCreati) {
		this.eventiCreati = eventiCreati;
		this.numeroEventiCreati = numeroEventiCreati;
		buildPanel();
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void buildPanel() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.PINK);
		gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWeights = new double[] { 1.0, 0.0 };
		mainPanel.setLayout(gbl_mainPanel);

		// Eventi creati
		eventiPanel = new TextSelectPanel(eventiCreati, numeroEventiCreati, "Ritira l'evento numero:", "Ritira");
		eventiPanel.setPreferredSize(DIM_TEXT);
		eventiCns = new GridBagConstraints();
		eventiCns.weighty = 3.0;
		eventiCns.weightx = 1.0;
		eventiCns.fill = GridBagConstraints.BOTH;
		eventiCns.gridx = 0;
		eventiCns.gridy = 0;
		mainPanel.add(eventiPanel, eventiCns);

		// BUTTON
		buttonPanel = new JPanel();
		boxLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
		buttonPanel.setLayout(boxLayout);
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

		btnEsci = new JButton("Indietro");
		buttonPanel.add(btnEsci);
		btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 19));
	}

	public JButton getBtnIndietro() {
		return btnEsci;
	}

	public void setBtnIndietro(JButton btnIndietro) {
		this.btnEsci = btnIndietro;
	}

	public TextSelectPanel getEventiPanel() {
		return eventiPanel;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		SpazioPersonale spazio = (SpazioPersonale) arg0;
		this.numeroEventiCreati = spazio.getEventiC().size();
		this.eventiCreati = ToStringUtil.toStringEventiCreati(MasterController.getRaccoltaE(),
				MasterController.getRaccoltaU().getUtenteByName(spazio.getProprietario()));

		eventiPanel.setText(eventiCreati);
		eventiPanel.setNumberSelection(numeroEventiCreati);
		eventiPanel.refresh();
	}

	public void successoRitiroMessage() {
		JOptionPane.showMessageDialog(null, SUCCESSO_RITIRO_MESSAGE, WARNING, JOptionPane.INFORMATION_MESSAGE);
	}

	public void nessunEventoMessage() {
		JOptionPane.showMessageDialog(null, NESSUN_EVENTO_MESSAGE, WARNING, JOptionPane.INFORMATION_MESSAGE);
	}

}