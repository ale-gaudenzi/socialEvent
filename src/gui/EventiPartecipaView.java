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
import javax.swing.JComboBox;

public class EventiPartecipaView implements View, Observer {
	private static final Dimension dimensioneEventi = new Dimension(400, 400);
	private static final Dimension dimensioneButton = new Dimension(200, 400);
	private static final String SUCCESSO_RITIRO_MESSAGE = "Ti sei ritirato con successo";
	private static final String NESSUN_EVENTO_MESSAGE = "Non stai partecipando a nessun evento";
	private int numeroEventiPartecipa;
	private String eventiPartecipa;

	private JPanel mainPanel;
	private GridBagLayout gbl_mainPanel;
	private TextSelectPanel eventiPanel;
	private GridBagConstraints eventiCns;
	private JPanel buttonPanel;
	private BoxLayout boxLayout;
	private GridBagConstraints buttonCns;
	private JButton btnIndietro;

	private JComboBox<Integer> combo;
	private JButton btnRitirati;

	/**
	 * @wbp.parser.entryPoint
	 */
	public EventiPartecipaView(String eventiPartecipa, int numeroEventiPartecipa) {
		this.eventiPartecipa = eventiPartecipa;
		this.numeroEventiPartecipa = numeroEventiPartecipa;
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

		// testo eventi
		eventiPanel = new TextSelectPanel(eventiPartecipa, numeroEventiPartecipa, "Ritirati dall'evento numero:",
				"Ritirati");
		eventiPanel.setPreferredSize(dimensioneEventi);
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
		buttonPanel.setPreferredSize(dimensioneButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		buttonCns = new GridBagConstraints();
		buttonCns.fill = GridBagConstraints.BOTH;
		buttonCns.weighty = 3.0;
		buttonCns.weightx = 0.5;
		buttonCns.gridx = 1;
		buttonCns.gridy = 0;
		mainPanel.add(buttonPanel, buttonCns);

		btnIndietro = new JButton("Indietro");
		buttonPanel.add(btnIndietro);
		btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 19));
	}

	public JButton getBtnIndietro() {
		return btnIndietro;
	}

	public void setBtnIndietro(JButton btnIndietro) {
		this.btnIndietro = btnIndietro;
	}

	public TextSelectPanel getEventiPanel() {
		return eventiPanel;
	}

	public void update(Observable arg0, Object arg1) {
		SpazioPersonale spazio = (SpazioPersonale) arg0;
		this.numeroEventiPartecipa = spazio.getEventiP().size();
		this.eventiPartecipa = ToStringUtil.toStringEventiPartecipa(MasterController.getRaccoltaE(),
				MasterController.getRaccoltaU().getUtenteByName(spazio.getProprietario()));

		eventiPanel.setText(eventiPartecipa);
		eventiPanel.setNumberSelection(numeroEventiPartecipa);
		eventiPanel.refresh();
	}

	public void successoRitiroMessage() {
		JOptionPane.showMessageDialog(null, SUCCESSO_RITIRO_MESSAGE, WARNING, JOptionPane.INFORMATION_MESSAGE);
	}

	public void nessunEventoMessage() {
		JOptionPane.showMessageDialog(null, NESSUN_EVENTO_MESSAGE, WARNING, JOptionPane.INFORMATION_MESSAGE);
	}

	public JButton getBtnRitirati() {
		return btnRitirati;
	}

	public void setBtnRitirati(JButton btnRitirati) {
		this.btnRitirati = btnRitirati;
	}

	public JComboBox<Integer> getCombo() {
		return combo;
	}

	public void setCombo(JComboBox<Integer> combo) {
		this.combo = combo;
	}

}