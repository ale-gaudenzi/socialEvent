package social;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Classe per la gestione dello spazio personale dell'utente
 * 
 * @author Alessandro Gaudenzi
 */
public class SpazioPersonale extends Observable implements Jsonable {
	private ArrayList<String> notifiche;
	private ArrayList<Integer> eventiP; //codici evento
	private ArrayList<Integer> eventiC;
	private String proprietario;

	/**
	 * Costruttore per lo spazio personale dell'utente
	 */
	public SpazioPersonale(Utente proprietario) {
		notifiche = new ArrayList<String>();
		eventiP = new ArrayList<Integer>();
		eventiC = new ArrayList<Integer>();
		this.setProprietario(proprietario.getNome());
	}
	
	public SpazioPersonale() {
		
	}

	public void addNotifica(String notifica) {
		notifiche.add(notifica);
		updated();
	}

	public void removeNotifica(int index) {
		notifiche.remove(index);
		updated();
	}
	
	public void updated() {
		setChanged();
		notifyObservers();
		clearChanged();
	}

	public void addEventoP(int e) {
		eventiP.add(e);
	}

	public void addEventoC(int e) {
		eventiC.add(e);
	}

	public void removeEventoP(int e) {
		eventiP.remove((Integer)e);
	}

	public void removeEventoC(int e) {
		eventiC.remove((Integer)e);
	}

	public ArrayList<String> getNotifiche() {
		return this.notifiche;
	}

	public ArrayList<Integer> getEventiP() {
		return this.eventiP;
	}

	public ArrayList<Integer> getEventiC() {
		return this.eventiC;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
}
