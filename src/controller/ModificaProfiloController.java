package controller;

import java.util.InputMismatchException;


import gui.ModificaProfiloView;
import social.Utente;
import utility.InputUtil;

/**
 * Classe controller per la vista della modifica profilo dell'utente
 * 
 * @author Alessandro Gaudenzi
 */
public class ModificaProfiloController implements Controller {
	ModificaProfiloView view;
	Utente acceduto;

	/**
	 * Costruttore per la classe ModificaProfiloController
	 * 
	 * @param view
	 * @param acceduto
	 */
	public ModificaProfiloController(ModificaProfiloView view, Utente acceduto) {
		this.view = view;
		this.acceduto = acceduto;
		initController();
	}

	/**
	 * Inizializza il controller aggiungendo un action listener per ogni elemento
	 * dell'ui
	 */
	@Override
	public void initController() {
		view.getBtnIndietro().addActionListener(e -> toSpazioPersonale());
		view.getBtnSalva().addActionListener(e -> salva());
	}

	public void salva() {
		int newEtaMin = 0;
		int newEtaMax = 0;
		int correnteEtaMax = acceduto.getProfilo().getEt‡Max();
		int correnteEtaMin = acceduto.getProfilo().getEt‡Min();

		try {
			if (!view.getTextEtaMax().getText().equals(null)) {
				newEtaMax = InputUtil.leggiInt(view.getTextEtaMax());
			if (!view.getTextEtaMin().getText().equals(null))
				newEtaMin = InputUtil.leggiInt(view.getTextEtaMin());
			}
		} catch (InputMismatchException e) {
			view.inputErrorMessage();
			return;
		}

		System.out.println("aa " + newEtaMin + newEtaMax);
		if (newEtaMin > newEtaMax || 
				(correnteEtaMin>newEtaMax && newEtaMin == 0) || 
				(newEtaMin>correnteEtaMax && newEtaMax == 0)) {
			view.etaImpossibileErrorMessage();
			return;
		}

		if (newEtaMin != 0) {
			acceduto.getProfilo().setEt‡Min(newEtaMin);
		}

		if (newEtaMax != 0) {
			acceduto.getProfilo().setEt‡Max(newEtaMax);
		}

		MasterController.save();
		view.successoMessage();
		toSpazioPersonale();

	}

	public void toSpazioPersonale() {
		MasterController.switchToSpazioPersonale();
	}

}
