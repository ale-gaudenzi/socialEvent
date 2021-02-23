package controller;

import gui.EventiPartecipaView;
import social.RaccoltaEventi;
import social.SpazioPersonale;
import social.Utente;
import utility.InputUtil;

/**
 * Classe controller per la vista degli eventi partecipati dall'utente
 * 
 * @author Alessandro Gaudenzi
 */
public class EventiPartecipaController implements Controller {
	EventiPartecipaView view;
	RaccoltaEventi raccoltaE;
	Utente utenteAcceduto;
	SpazioPersonale spazioAcceduto;

	/**
	 * Costruttore per la classe SpazioPersonaleController
	 * 
	 * @param spView   
	 * @param raccoltaE
	 * @param spazioUtenteAcceduto
	 */
	public EventiPartecipaController(EventiPartecipaView view, RaccoltaEventi raccoltaE, Utente utenteAcceduto) {
		this.view = view;
		this.raccoltaE = raccoltaE;
		this.utenteAcceduto = utenteAcceduto;
		this.spazioAcceduto = utenteAcceduto.getSpazioP();
		initController();
	}

	/**
	 * Inizializza il controller aggiungendo un action listener per ogni elemento dell'ui
	 */
	@Override
	public void initController() {
		view.getBtnIndietro().addActionListener(e -> toSpazioPersonale());
		view.getEventiPanel().getBtnSelect().addActionListener(e -> ritiratiDaEvento());
	}

	public void toSpazioPersonale() {
		MasterController.switchToSpazioPersonale();
	}

	public void ritiratiDaEvento() {
		if (spazioAcceduto.getEventiP().size() != 0) {
			int indiceEvento = InputUtil.leggiInteroDaCombo(view.getEventiPanel().getCombo()) - 1;
			int codiceEvento = spazioAcceduto.getEventiP().get(indiceEvento);
			raccoltaE.getEventoFromCode(codiceEvento).removePartecipante(utenteAcceduto);
			spazioAcceduto.getEventiP().remove(indiceEvento);
			spazioAcceduto.updated();
			view.successoRitiroMessage();
		} else
			view.nessunEventoMessage();
		MasterController.save();
	}

}
