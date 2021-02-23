package controller;

import java.util.InputMismatchException;


import javax.swing.JCheckBox;
import gui.RegistrationView;
import social.Profilo;
import social.RaccoltaUtenti;
import social.Utente;
import utility.InputUtil;

/**
 * Classe controller per la vista di registrazione dell'utente
 * 
 * @author Alessandro Gaudenzi
 */
public class RegistrationController implements Controller {
	RegistrationView view;
	RaccoltaUtenti raccoltaU;
	
	/**
	 * Costruttore per la classe RegistrationController
	 * 
	 * @param spView   
	 * @param raccoltaE
	 * @param spazioUtenteAcceduto
	 */
	public RegistrationController(RegistrationView regView, RaccoltaUtenti raccoltaUtenti) {
		view = regView;
		raccoltaU = raccoltaUtenti;
		initController();
	}

	/**
	 * Inizializza il controller aggiungendo un action listener per ogni elemento dell'ui
	 */
	public void initController() {
		view.getBtnRegistrati().addActionListener(e -> tryRegistration());
		view.getBtnEsci().addActionListener(e -> toLogin());
	}

	public void tryRegistration() {
		String nome = null;
		String password = null;
		String nick = null;

		try {
			nome = InputUtil.leggiStringNonVuota(view.getTextFieldName());
			password = InputUtil.leggiStringNonVuota(view.getTextFieldPassword());
			nick = InputUtil.leggiStringNonVuota(view.getTextFieldNick());
		} catch(InputMismatchException e) {
			view.obligatoryInputErrorMessage();
			return;
		}
		
		Profilo profilo = new Profilo(nick);
		
		if (view.getTextFieldEtaMin().getText().length() != 0) {
			try{
				int etaMin = InputUtil.leggiInt(view.getTextFieldEtaMin());
				profilo.setEt‡Min(etaMin);
			}catch (InputMismatchException e ) {
				view.inputErrorMessage();
				return;
			}	
		}
		
		if (view.getTextFieldEtaMax().getText().length() != 0) {
			try {
				int etaMax = InputUtil.leggiInt(view.getTextFieldEtaMax());
				profilo.setEt‡Max(etaMax);
			} catch(InputMismatchException e) {
				view.inputErrorMessage();
				return;
			}
			
		}
		
		for(int i = 0; i < view.getCheckBoxInteressi().size(); i++) {
			JCheckBox checkBoxI = view.getCheckBoxInteressi().get(i);
			if(checkBoxI.isSelected())
				profilo.addInteressi(i);
		}

		Utente utente = new Utente(nome, password, profilo);
		
		if(raccoltaU.nomeEsistente(nome)) {
			view.nomeUtentePresenteMessage();
		}else {
			raccoltaU.addUtente(utente);
			MasterController.save();
			view.successRegistrationMessage();
		}
		
		toLogin();
	}

	public void toLogin() {
		MasterController.switchToLoginView();
	}
}
