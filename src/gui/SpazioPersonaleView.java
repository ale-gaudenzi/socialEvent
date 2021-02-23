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

public class SpazioPersonaleView implements View, Observer {
	private static final Dimension dimensioneNotifiche = new Dimension(400, 400);
	private static final Dimension dimensioneButton = new Dimension(200, 400);
	private static final String SUCCESSO_MESSAGE = "Eliminazione avvenuta con successo";
	private static final String NO_NOTIFICHE_MESSAGE = "Non possiedi notifiche da eliminare";

	
	private String notifiche;
	private int numeroNotifiche;
	
	private JPanel mainPanel;
	private GridBagLayout gbl_mainPanel;
	private JPanel buttonPanel;
	private GridBagConstraints buttonCns;
	private BoxLayout boxLayout;
	private JButton btnEventiPartecipa;
	private JButton btnEventiCreati;
	private JButton btnModificaProfilo;
	private JButton btnIndietro;
	
	private TextSelectPanel notifichePanel;

	private GridBagConstraints notificheCns;

	/**
	 * @wbp.parser.entryPoint
	 */
	public SpazioPersonaleView(String notifiche, int numeroNotifiche) {
		this.notifiche = notifiche;
		this.numeroNotifiche = numeroNotifiche;
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

		// NOTIFICHE
		notifichePanel = new TextSelectPanel(notifiche, numeroNotifiche, "Elimina notifica numero:", "Elimina");
		notifichePanel.setPreferredSize(dimensioneNotifiche);
		notificheCns = new GridBagConstraints();
		notificheCns.weighty = 3.0;
		notificheCns.weightx = 1.0;
		notificheCns.fill = GridBagConstraints.BOTH;
		notificheCns.gridx = 0;
		notificheCns.gridy = 0;
		mainPanel.add(notifichePanel, notificheCns);

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
		btnEventiPartecipa = new JButton("Eventi partecipati");
		buttonPanel.add(btnEventiPartecipa);
		btnEventiPartecipa.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnEventiCreati = new JButton("Eventi creati");
		buttonPanel.add(btnEventiCreati);
		btnEventiCreati.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnModificaProfilo = new JButton("ModificaProfilo");
		buttonPanel.add(btnModificaProfilo);
		btnModificaProfilo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnIndietro = new JButton("Indietro");
		buttonPanel.add(btnIndietro);
		btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 19));
	}

	public JButton getBtnEventiPartecipa() {
		return btnEventiPartecipa;
	}

	public void setBtnEventiPartecipa(JButton btnEventiPartecipati) {
		this.btnEventiPartecipa = btnEventiPartecipati;
	}

	public JButton getBtnEventiCreati() {
		return btnEventiCreati;
	}

	public void setBtnEventiCreati(JButton btnEventiCreati) {
		this.btnEventiCreati = btnEventiCreati;
	}

	public JButton getBtnModificaProfilo() {
		return btnModificaProfilo;
	}

	public void setBtnModificaProfilo(JButton btnModificaProfilo) {
		this.btnModificaProfilo = btnModificaProfilo;
	}

	public JButton getBtnIndietro() {
		return btnIndietro;
	}

	public void setBtnIndietro(JButton btnEsci) {
		this.btnIndietro = btnEsci;
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		SpazioPersonale spazio = (SpazioPersonale) arg0;
		notifiche = ToStringUtil.toStringNotifiche(MasterController.getRaccoltaU().getUtenteByName(spazio.getProprietario()));
		numeroNotifiche = spazio.getNotifiche().size();
		
		notifichePanel.setText(notifiche);
		notifichePanel.setNumberSelection(numeroNotifiche);
		notifichePanel.refresh();
	}
	
	public void successoEliminazioneMessage() {
		JOptionPane.showMessageDialog(null, SUCCESSO_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void notificheVuoteMessage() {
		JOptionPane.showMessageDialog(null, NO_NOTIFICHE_MESSAGE, WARNING,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public TextSelectPanel getNotifichePanel() {
		return notifichePanel;
	}


}