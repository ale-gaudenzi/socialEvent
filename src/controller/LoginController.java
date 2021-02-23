package controller;

import gui.LoginView;
import social.RaccoltaUtenti;

/**
 * Classe controller per la vista del login dell'utente
 * 
 * @author Alessandro Gaudenzi
 */
public class LoginController implements Controller {
	LoginView view;
	RaccoltaUtenti raccoltaU;
	
	/**
	 * Costruttore per la classe SpazioPersonaleController
	 * 
	 * @param loginView
	 * @param raccoltaUtenti
	 */
	public LoginController(LoginView loginView, RaccoltaUtenti raccoltaUtenti) {
		view = loginView;
		raccoltaU = raccoltaUtenti;
		initController();
	}
	
	/**
	 * Inizializza il controller aggiungendo un action listener per ogni elemento dell'ui
	 */
	public void initController() {
		 view.getBtnAccedi().addActionListener(e -> tryAccess());
		 view.getBtnRegistrati().addActionListener(e -> toRegisterView());
		 view.getBtnReset().addActionListener(e->reset());
	}
	
	private void tryAccess() {
		String nome = view.getTextFieldName().getText();
		String password = view.getTextFieldPassword().getText();
		
		if(raccoltaU.accessoConsentito(nome, password)) {
			MasterController.setUtenteAcceduto(nome);
			MasterController.switchToMainMenuView();
		}
		else {
			 view.utenteNonTrovatoErrorMessage();
		}
	}
	
	private void toRegisterView() {
		MasterController.switchToRegistrationView();
	}
		
	private void reset() {
		MasterController.reset();
		view.successoResetMessage();
	}
	
}
