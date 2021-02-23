package social;

import java.util.HashSet;

/**
 * Classe per la gestione del profilo utente
 * 
 * @author Alessandro Gaudenzi
 */
public class Profilo implements Jsonable{
	private String nickname;
	private int et‡Min;
	private int et‡Max;
	private HashSet<Integer> interessi;
	private HashSet<Integer> partecipato;
	
	/**
	 * Costruttore per il profilo utente, prende l'unico paramentro obbligatorio nickname
	 * 
	 * @param nickname
	 */
	public Profilo(String nickname) {
		this.interessi = new HashSet<Integer>();
		this.partecipato = new HashSet<Integer>();
		this.nickname = nickname;
	}

	public void removeInteressi(int indiceInteresse) {
		interessi.remove((Integer) indiceInteresse);
	}

	public void addInteressi(int indiceInteresse) {
		interessi.add((Integer) indiceInteresse);
	}

	public void addPartecipato(int indiceTipoEventoPartecipato) {
		partecipato.add((Integer) indiceTipoEventoPartecipato);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getEt‡Min() {
		return et‡Min;
	}

	public void setEt‡Min(int et‡Min) {
		this.et‡Min = et‡Min;
	}

	public int getEt‡Max() {
		return et‡Max;
	}

	public void setEt‡Max(int et‡max) {
		this.et‡Max = et‡max;
	}

	public HashSet<Integer> getPartecipato() {
		return partecipato;
	}

	public void setPartecipato(HashSet<Integer> partecipato) {
		this.partecipato = partecipato;
	}

	public HashSet<Integer> getInteressi() {
		return interessi;
	}
	
	public void setInteressi(HashSet<Integer> interessi) {
		this.interessi = interessi;
	}

	public Profilo() {
		
	}
}