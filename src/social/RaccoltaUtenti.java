package social;

import java.io.*;
import java.util.ArrayList;
import utility.JsonUtil;
import utility.ToStringUtil;

/**
 * Classe per la gestione della totalità degli utenti iscritti
 * 
 * @author Alessandro Gaudenzi
 */
public class RaccoltaUtenti implements Jsonable {
	private ArrayList<Utente> raccoltaU;

	/**
	 * Crea una nuova raccolta utenti se non esiste un file salvato, altrimenti
	 * carica la raccolta dal file indicato
	 * 
	 * @param nomefile è nome del file da cui caricare la raccolta
	 */
	public RaccoltaUtenti(String nomefile) {
		try {
			RaccoltaUtenti loaded = new RaccoltaUtenti();
			loaded = (RaccoltaUtenti) JsonUtil.loadJSON(nomefile);
			this.raccoltaU = loaded.getRaccoltaU();
		} catch (IOException e) {
			this.raccoltaU = new ArrayList<Utente>();
			JsonUtil.saveJSON(nomefile, this);
		}
	}

	/**
	 * Crea una nuova raccolta utenti da zero
	 * 
	 */
	public RaccoltaUtenti() {
		this.raccoltaU = new ArrayList<Utente>();
	}

	/**
	 * Invita gli utenti ad un evento se questi hanno esso tra gli interessi oppure
	 * opzionalmente se hanno già partecipato a iniziative dello stesso tipo
	 * 
	 * @param includiPartecipati
	 * @param evento
	 */
	public void invita(boolean includiPartecipati, Evento evento) {
		int tipoEvento = evento.getTipoEvento();
		if (includiPartecipati) {
			for (int i = 0; i < raccoltaU.size(); i++) {
				Utente daInvitare = raccoltaU.get(i);
				if (daInvitare.getProfilo().getInteressi().contains(tipoEvento)
						|| daInvitare.getProfilo().getPartecipato().contains(tipoEvento))
					daInvitare.getSpazioP().addNotifica(ToStringUtil.notificaInvito(evento, daInvitare));
			}
		} else {
			for (int i = 0; i < raccoltaU.size(); i++) {
				Utente daInvitare = raccoltaU.get(i);
				if (daInvitare.getProfilo().getInteressi().contains(tipoEvento))
					daInvitare.getSpazioP().addNotifica(ToStringUtil.notificaInvito(evento, daInvitare));
			}
		}

	}

	/**
	 * Iscrive utente al social network
	 * 
	 * @pre utente da iscrivere non ha un nome già presente
	 * @post utente da iscrivere viene iscritto
	 * @return boolean per confermare iscrizione
	 */
	public void addUtente(Utente daAggiungere) {
		if (!nomeEsistente(daAggiungere.getNome())) {
			raccoltaU.add(daAggiungere);
		}
	}

	/**
	 * Verifica se le credenziali dell'utente immesse corrispondano a un utente
	 * iscritto
	 * 
	 * @param nome     dell'utente di cui bisogna verificare l'accesso
	 * @param password dell'utente di cui bisogna verificare l'accesso
	 * @return bool se credenziali sono corrette o meno
	 */
	public boolean accessoConsentito(String nome, String password) {
		for (int i = 0; i < raccoltaU.size(); i++) {
			Utente utenteI = raccoltaU.get(i);
			String nomeI = utenteI.getNome();
			String passwordI = utenteI.getPassword();
			if (nomeI.equals(nome) && passwordI.equals(password)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica che il nome utente inserito esista già nella raccolta
	 * 
	 * @param nome
	 * @return bool se nome esiste già
	 */
	public boolean nomeEsistente(String nome) {
		if (raccoltaU.size() == 0) {
			return false;
		} else {
			for (int i = 0; i < raccoltaU.size(); i++) {
				Utente utenteI = raccoltaU.get(i);
				String nomeI = utenteI.getNome();
				if (nomeI.equals(nome))
					return true;
			}
			return false;
		}
	}

	/**
	 * Ritorna l'oggetto utente in base al nome
	 * 
	 * @param utenteDaCercare
	 * @pre utente da cercare è presente nella raccolta
	 * @post @nochange
	 * @return Utente cercato dal nome
	 */
	public Utente getUtenteByName(String utenteDaCercare) {
		if (nomeEsistente(utenteDaCercare)) {
			for (int i = 0; i < raccoltaU.size(); i++) {
				Utente utenteI = raccoltaU.get(i);
				String nomeI = utenteI.getNome();
				if (nomeI.equals(utenteDaCercare))
					return utenteI;
			}
		} else {
			return null;
		}
		return null;
	}

	/**
	 * Aggiorna l'utente modificato nella raccolta, utile per mostrare cambiamenti
	 * durante l'esecuzione
	 * 
	 * @param daAggiornare utente modificato
	 */
	public void aggiornaUtente(Utente daAggiornare) {
		int indice = indiceUtente(daAggiornare);
		raccoltaU.remove(indice);
		addUtente(daAggiornare);
	}

	/**
	 * Cerca l'indice corrispondente all'utente inserito
	 * 
	 * @param utenteDaTrovare
	 * @return int corrispondente all'indice utente
	 */
	public int indiceUtente(Utente utenteDaTrovare) {
		Integer index = null;
		for (int i = 0; i < raccoltaU.size(); i++) {
			String nomeDaTrovare = utenteDaTrovare.getNome();
			Utente utenteI = raccoltaU.get(i);
			String nomeI = utenteI.getNome();
			if (nomeDaTrovare.equals(nomeI))
				index = i;
		}
		return index;
	}

	public ArrayList<Utente> getRaccoltaU() {
		return raccoltaU;
	}

}
