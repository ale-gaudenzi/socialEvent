package controller;

import java.util.HashMap;

import javax.swing.JOptionPane;

import gui.MainMenuView;
import gui.SelectQuoteView;
import social.Evento;
import social.EventoBase;
import social.EventoQuote;
import social.RaccoltaEventi;
import social.RaccoltaUtenti;
import social.Utente;
import utility.InputUtil;

/**
 * Classe controller per la vista del menu principale
 * 
 * @author Alessandro Gaudenzi
 */
public class MainMenuController implements Controller {
	MainMenuView view;
	RaccoltaUtenti raccoltaU;
	RaccoltaEventi raccoltaE;
	Utente utenteAcceduto;
	public static SelectQuoteView selectQuoteView;

	/**
	 * Costruttore per la classe MainMenuController
	 * 
	 * @param view
	 * @param raccoltaEventi
	 * @param raccoltaUtenti
	 * @param utenteAcceduto
	 */
	public MainMenuController(MainMenuView view, RaccoltaUtenti raccoltaUtenti, RaccoltaEventi raccoltaEventi,
			Utente utenteAcceduto) {
		this.view = view;
		this.raccoltaU = raccoltaUtenti;
		this.raccoltaE = raccoltaEventi;
		this.utenteAcceduto = utenteAcceduto;
		initController();
	}

	/**
	 * Inizializza il controller aggiungendo un action listener per ogni elemento
	 * dell'ui
	 */
	public void initController() {
		view.getBtnSpaziopersonale().addActionListener(e -> toSpazioPersonale());
		view.getBtnCreaEvento().addActionListener(e -> toCreaEvento());
		view.getBachecaPanel().getBtnSelect().addActionListener(e -> partecipaAdEvento());
		view.getBtnEsci().addActionListener(e -> toLogin());
	}

	public void partecipaAdEvento() {
		int indiceEvento = InputUtil.leggiInteroDaCombo(view.getBachecaPanel().getCombo()) - 1;
		Evento daPartecipare = raccoltaE.getRaccoltaE().get(indiceEvento);
		if (daPartecipare.isPartecipante(utenteAcceduto)) {
			view.utenteGiaPartecipaErrorMessage();
			return;
		} else {
			try {
				if (daPartecipare instanceof EventoQuote && daPartecipare.getClass() != EventoBase.class) {
					HashMap<String, Double> quoteSelezionate = selezionaQuote(daPartecipare);
					((EventoQuote) daPartecipare).iscriviUtente(utenteAcceduto, quoteSelezionate);
				} else if (daPartecipare instanceof EventoBase) {
					daPartecipare.iscriviUtente(utenteAcceduto);
				}
				view.successoIscrizioneMessage();
			} catch (Exception e) { // poi cambiare con eccezioni ad hoc
				e.printStackTrace();
				view.fallimentoIscrizioneMessage();
			}
		}
		MasterController.save();
	}

	public HashMap<String, Double> selezionaQuote(Evento e) {
		showSelezioneQuote(((EventoQuote) e).getQuoteAggiuntive());
		HashMap<String, Double> quote = InputUtil.leggiMapStringDoubleCheck(selectQuoteView.getDtm());

		return quote;
	}

	public void toCreaEvento() {
		MasterController.switchToEventoStdView();
	}

	public void toLogin() {
		MasterController.deleteObservers();
		MasterController.switchToLoginView();
	}

	public void toSpazioPersonale() {
		MasterController.switchToSpazioPersonale();
	}

	public static void showSelezioneQuote(HashMap<String, Double> quote) {
		selectQuoteView = new SelectQuoteView(quote);
		JOptionPane.showConfirmDialog(null, selectQuoteView.getMainPanel(), "Quote aggiuntive : ",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	}
}
