package controller;

import gui.SpazioPersonaleView;
import social.RaccoltaEventi;
import social.SpazioPersonale;
import utility.InputUtil;

/**
 * Classe controller per la vista dello spazio personale dell'utente
 * 
 * @author Alessandro Gaudenzi
 */
public class SpazioPersonaleController implements Controller {
	SpazioPersonaleView view;
	RaccoltaEventi raccoltaE;
	SpazioPersonale acceduto;

	/**
	 * Costruttore per la classe SpazioPersonaleController
	 * 
	 * @param spView
	 * @param raccoltaE
	 * @param spazioUtenteAcceduto
	 */
	public SpazioPersonaleController(SpazioPersonaleView spView, RaccoltaEventi raccoltaE,
			SpazioPersonale spazioUtenteAcceduto) {
		this.view = spView;
		this.raccoltaE = raccoltaE;
		this.acceduto = spazioUtenteAcceduto;
		initController();
	}

	/**
	 * Inizializza il controller aggiungendo un action listener per ogni elemento
	 * dell'ui
	 */
	public void initController() {
		view.getBtnModificaProfilo().addActionListener(e -> modificaProfilo());
		view.getBtnEventiCreati().addActionListener(e -> toEventiCreati());
		view.getBtnEventiPartecipa().addActionListener(e -> toEventiPartecipa());
		view.getBtnIndietro().addActionListener(e -> toMainMenu());
		view.getNotifichePanel().getBtnSelect().addActionListener(e -> eliminaNotifica());
	}

	public void modificaProfilo() {
		MasterController.switchToModificaProfiloView();
	}

	public void toEventiCreati() {
		MasterController.switchToEventiCreatiView();
	}

	public void toEventiPartecipa() {
		MasterController.switchToEventiPartecipaView();
	}

	public void toMainMenu() {
		MasterController.switchToMainMenuView();
	}

	public void eliminaNotifica() {
		if (acceduto.getNotifiche().size() != 0) {
			int daEliminare = InputUtil.leggiInteroDaCombo(view.getNotifichePanel().getCombo());
			acceduto.removeNotifica(daEliminare);
			view.successoEliminazioneMessage();
		} else
			view.notificheVuoteMessage();
	}
}
