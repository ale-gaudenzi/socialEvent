package controller;

import java.util.ArrayList;
import gui.EventiCreatiView;
import gui.EventiPartecipaView;
import gui.LoginView;
import gui.MainMenuView;
import gui.MasterView;
import gui.ModificaProfiloView;
import gui.EventoView;
import gui.RegistrationView;
import gui.SpazioPersonaleView;
import social.Config;
import social.RaccoltaEventi;
import social.RaccoltaUtenti;
import social.SpazioPersonale;
import social.Utente;
import utility.JsonUtil;
import utility.ToStringUtil;

/**
 * Classe che gestisce la visualizzazione delle varie viste in base alla
 * navigazione dell'utente nell'applicazione e la creazione dei relativi controller
 * 
 * @author Alessandro Gaudenzi
 */
public class MasterController {
	public static MasterView view;
	public static EventiCreatiView eventiCreatiView;
	public static EventiPartecipaView eventiPartecipaView;
	public static LoginView loginView;
	public static MainMenuView mainMenuView;
	public static ModificaProfiloView modificaProfiloView;
	public static EventoView eventoStdView;
	public static RegistrationView registrationView;
	public static SpazioPersonaleView spazioPersonaleView;
	public static LoginController loginController;
	public static RegistrationController registrationController;
	public static MainMenuController mainMenuController;
	public static SpazioPersonaleController spazioPersonaleController;
	public static EventoController eventoStdController;
	public static EventiCreatiController eventiCreatiController;
	public static EventiPartecipaController eventiPartecipaController;
	public static ModificaProfiloController modificaProfiloController;
	public static RaccoltaUtenti raccoltaU;
	public static RaccoltaEventi raccoltaE;
	public static Utente utenteAcceduto;
	public static SpazioPersonale spazioAcceduto;

	/**
	 * Costruttore per la classe MasterController
	 */
	public MasterController() {
		raccoltaU = new RaccoltaUtenti(Config.getInstance().getRaccoltaU());
		raccoltaE = new RaccoltaEventi(Config.getInstance().getRaccoltaE());
		view = new MasterView();
	}

	/**
	 * Salva le due raccolte contenenti i dati degli utenti e degli eventi
	 */
	public static void save() {
		JsonUtil.saveJSON(Config.getInstance().getRaccoltaU(), raccoltaU);
		JsonUtil.saveJSON(Config.getInstance().getRaccoltaE(), raccoltaE);
	}

	/**
	 * Crea da zero due nuove raccolte, eliminando tutti i dati contenuti
	 */
	public static void reset() {
		raccoltaU = new RaccoltaUtenti();
		raccoltaE = new RaccoltaEventi();
		save();
	}

	/**
	 * Crea la vista contenitore per le altre e mostra la schermata di login
	 */
	public void init() {
		view.build();
		switchToLoginView();
	}

	/**
	 * Imposta l'utente acceduto
	 */
	public static void setUtenteAcceduto(String nome) {
		ArrayList<Utente> raccolta = raccoltaU.getRaccoltaU();
		for (int i = 0; i < raccolta.size(); i++) {
			Utente utenteI = raccolta.get(i);
			if (utenteI.getNome().equals(nome)) {
				utenteAcceduto = utenteI;
				spazioAcceduto = utenteI.getSpazioP();
			}
		}
	}

	/**
	 * Elimina tutti gli observer (dopo che un utente si è scollegato)
	 */
	public static void deleteObservers() {
		if (spazioAcceduto.countObservers() != 0) {
			spazioAcceduto.deleteObservers();
		}
		if (raccoltaE.countObservers() != 0)
			raccoltaE.deleteObservers();
	}

	/**
	 * Cambia la vista corrente nella vista di login, creando il controller
	 * corrispondente se non esiste
	 */
	public static void switchToLoginView() {
		if (loginView == null || loginController == null) {
			loginView = new LoginView();
			loginController = new LoginController(loginView, raccoltaU);
		}
		view.changeView(loginView);
	}

	/**
	 * Cambia la vista corrente nella vista di registrazione, creando il controller
	 * corrispondente se non esiste
	 */
	public static void switchToRegistrationView() {
		if (registrationView == null || registrationController == null) {
			registrationView = new RegistrationView(RaccoltaEventi.TIPI_EVENTO);
			registrationController = new RegistrationController(registrationView, raccoltaU);
		}
		view.changeView(registrationView);
	}

	/**
	 * Cambia la vista corrente nella vista di sapzio personale, creando il
	 * controller corrispondente se non esiste
	 */
	public static void switchToSpazioPersonale() {
		if (spazioPersonaleView == null || spazioPersonaleController == null) {
			spazioAcceduto = utenteAcceduto.getSpazioP();
			spazioPersonaleView = new SpazioPersonaleView(ToStringUtil.toStringNotifiche(utenteAcceduto),
					spazioAcceduto.getNotifiche().size());
			spazioPersonaleController = new SpazioPersonaleController(spazioPersonaleView, raccoltaE, spazioAcceduto);
			spazioAcceduto.addObserver(spazioPersonaleView);
		}
		view.changeView(spazioPersonaleView);
	}

	/**
	 * Cambia la vista corrente nella vista di menu principale, creando il
	 * controller corrispondente se non esiste
	 */
	public static void switchToMainMenuView() {
		if (mainMenuView == null || mainMenuController == null) {
			mainMenuView = new MainMenuView(ToStringUtil.toStringRaccoltaEventi(raccoltaE),
					raccoltaE.getRaccoltaE().size());
			mainMenuController = new MainMenuController(mainMenuView, raccoltaU, raccoltaE, utenteAcceduto);
			raccoltaE.addObserver(mainMenuView);
		}

		view.changeView(mainMenuView);
	}

	/**
	 * Cambia la vista corrente nella vista di creazione evento, creando il
	 * controller corrispondente se non esiste
	 */
	public static void switchToEventoStdView() {
		if (eventoStdView == null || eventoStdController == null) {
			eventoStdView = new EventoView(RaccoltaEventi.TIPI_EVENTO.length);
			eventoStdController = new EventoController(eventoStdView, raccoltaU, raccoltaE, utenteAcceduto);
		}
		view.changeView(eventoStdView);
	}

	/**
	 * Cambia la vista corrente nella vista di eventi creati, creando il controller
	 * corrispondente se non esiste
	 */
	public static void switchToEventiCreatiView() {
		if (eventiCreatiView == null || eventiCreatiController == null) {
			eventiCreatiView = new EventiCreatiView(ToStringUtil.toStringEventiCreati(raccoltaE, utenteAcceduto),
					spazioAcceduto.getEventiC().size());
			eventiCreatiController = new EventiCreatiController(eventiCreatiView, raccoltaE, spazioAcceduto);
			spazioAcceduto.addObserver(eventiCreatiView);
		}
		view.changeView(eventiCreatiView);
	}

	/**
	 * Cambia la vista corrente nella vista di eventi partecipati, creando il
	 * controller corrispondente se non esiste
	 */
	public static void switchToEventiPartecipaView() {
		if (eventiPartecipaView == null || eventiPartecipaController == null) {
			eventiPartecipaView = new EventiPartecipaView(
					ToStringUtil.toStringEventiPartecipa(raccoltaE, utenteAcceduto),
					spazioAcceduto.getEventiP().size());
			eventiPartecipaController = new EventiPartecipaController(eventiPartecipaView, raccoltaE, utenteAcceduto);
			spazioAcceduto.addObserver(eventiPartecipaView);
		}
		view.changeView(eventiPartecipaView);
	}

	/**
	 * Cambia la vista corrente nella vista di profilo, creando il controller
	 * corrispondente se non esiste
	 */
	public static void switchToModificaProfiloView() {
		if (modificaProfiloView == null || modificaProfiloController == null) {
			modificaProfiloView = new ModificaProfiloView();
			modificaProfiloController = new ModificaProfiloController(modificaProfiloView, utenteAcceduto);
		}
		view.changeView(modificaProfiloView);
	}

	public static RaccoltaEventi getRaccoltaE() {
		return raccoltaE;
	}

	public static RaccoltaUtenti getRaccoltaU() {
		return raccoltaU;
	}
}
