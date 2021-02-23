package controller;

import gui.EventiCreatiView;
import social.RaccoltaEventi;
import social.SpazioPersonale;
import utility.InputUtil;

/**
 * Classe controller per la vista degli eventi creati dall'utente
 * 
 * @author Alessandro Gaudenzi
 */
public class EventiCreatiController implements Controller {
	EventiCreatiView view;
	RaccoltaEventi raccoltaE;
	SpazioPersonale acceduto;

	/**
	 * Costruttore per la classe EventiCreatiController
	 * 
	 * @param view
	 * @param raccoltaE
	 * @param acceduto
	 */
	public EventiCreatiController(EventiCreatiView view, RaccoltaEventi raccoltaE, SpazioPersonale acceduto) {
		this.view = view;
		this.raccoltaE = raccoltaE;
		this.acceduto = acceduto;
		initController();
	}

	/**
	 * Inizializza il controller aggiungendo un action listener per ogni elemento
	 * dell'ui
	 */
	@Override
	public void initController() {
		view.getEventiPanel().getBtnSelect().addActionListener(e -> ritiraEvento());
		view.getBtnIndietro().addActionListener(e -> toSpazioPersonale());
	}

	public void toSpazioPersonale() {
		MasterController.switchToSpazioPersonale();
	}

	public void ritiraEvento() {
		if (acceduto.getEventiC().size() != 0) {
			int indiceEvento = InputUtil.leggiInteroDaCombo(view.getEventiPanel().getCombo()) - 1;
			int codiceEvento = acceduto.getEventiC().get(indiceEvento);
			raccoltaE.ritiraEvento(codiceEvento);
			view.successoRitiroMessage();
			acceduto.getEventiC().remove((Integer) codiceEvento);
			acceduto.updated();
		} else
			view.nessunEventoMessage();
		MasterController.save();
	}
}
