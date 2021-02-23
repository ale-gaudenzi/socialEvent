package social;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.util.*;
import utility.*;

/**
 * Classe per la gestione della totalità degli eventi creati
 * 
 * @author Alessandro Gaudenzi
 */
public class RaccoltaEventi extends Observable implements Jsonable {
	private ArrayList<Evento> raccoltaE;
	public static final String[] TIPI_EVENTO = { "Partita di calcio", "Motoraduno", "Gita" };
	public static final String[] STATI_EVENTO = { "Aperto", "Chiuso", "Fallito", "Concluso", "Ritirato" };

	/**
	 * Crea una nuova raccolta eventi se non esiste un file salvato, altrimenti
	 * carica la raccolta dal file indicato
	 * 
	 * @param nomefile è nome del file da cui caricare la raccolta
	 */
	public RaccoltaEventi(String nomefile) {
		try {
			RaccoltaEventi loaded = new RaccoltaEventi();
			loaded = (RaccoltaEventi) JsonUtil.loadJSON(nomefile);
			this.raccoltaE = loaded.getRaccoltaE();
		} catch (IOException e) {
			e.printStackTrace();
			this.raccoltaE = new ArrayList<Evento>();
			JsonUtil.saveJSON(nomefile, this);
		}
	}
	
	/**
	 * Crea una nuova raccolta eventi da zero
	 * 
	 */
	public RaccoltaEventi() {
		this.raccoltaE = new ArrayList<Evento>();
	}

	/**
	 * Verifica per ogni evento se sussistono le condizioni di conclusione e in caso
	 * aggiorna lo stato, manda le notifiche oppurtune e rimuove l'evento dallo
	 * spazio personale dei partecipanti
	 */
	public void eventiConclusi() {
		Clock.systemDefaultZone();

		for (int i = 0; i < raccoltaE.size(); i++) {
			Evento evento = raccoltaE.get(i);
			LocalDate inizio = evento.getDataInizio();
			LocalDate fine = evento.getDataFine();
			if (ClockUtil.now().isAfter(inizio) || (fine != null && ClockUtil.now().isAfter(fine))) {
				ArrayList<Utente> partecipanti = evento.getPartecipanti();
				for (int j = 0; j < partecipanti.size(); j++) {
					Utente utenteJ = partecipanti.get(j);
					SpazioPersonale spazioJ = utenteJ.getSpazioP();
					spazioJ.addNotifica(ToStringUtil.notificaConcluso(evento, partecipanti.get(j)));
					spazioJ.removeEventoP(evento.getCode());
				}
				Utente creatore = evento.getCreatore();
				SpazioPersonale spazioCreatore = creatore.getSpazioP();
				spazioCreatore.removeEventoC(evento.getCode());
				evento.setStatoEvento(3);
			}
		}
	}

	/**
	 * Verifica per ogni evento se sussistono le condizioni di fallimento e in caso
	 * aggiorna lo stato, manda le notifiche oppurtune e rimuove l'evento dallo
	 * spazio personale dei partecipanti
	 */
	public void eventiFalliti() {
		Clock.systemDefaultZone();

		for (int i = 0; i < raccoltaE.size(); i++) {
			Evento evento = raccoltaE.get(i);
			ArrayList<Utente> partecipanti = evento.getPartecipanti();
			int numPartecipanti = partecipanti.size();
			int maxPartecipanti = evento.getMaxPartecipanti();
			LocalDate scadenzaIscrizioneI = evento.getScadenzaIscrizione();

			if (maxPartecipanti > numPartecipanti && ClockUtil.now().isAfter(scadenzaIscrizioneI)) {
				for (int j = 0; j < partecipanti.size(); j++) {
					Utente utenteJ = partecipanti.get(j);
					SpazioPersonale spazioJ = utenteJ.getSpazioP();
					spazioJ.addNotifica(ToStringUtil.notificaFallito(evento, partecipanti.get(j)));
					spazioJ.removeEventoP(evento.getCode());
				}
				Utente creatore = evento.getCreatore();
				SpazioPersonale spazioCreatore = creatore.getSpazioP();
				spazioCreatore.removeEventoC(evento.getCode());
				evento.setStatoEvento(2);
			}
		}
	}

	/**
	 * Verifica per ogni evento se sussistono le condizioni di chiusura e in caso
	 * aggiorna lo stato, manda le notifiche oppurtune e rimuove l'evento dallo
	 * spazio personale dei partecipanti
	 */
	public void eventiChiusi() {
		Clock.systemDefaultZone();

		for (int i = 0; i < raccoltaE.size(); i++) {
			Evento evento = raccoltaE.get(i);
			LocalDate scadenzaIscrizione = evento.getScadenzaIscrizione();
			ArrayList<Utente> partecipanti = evento.getPartecipanti();
			int numPartecipanti = partecipanti.size();
			int maxPartecipanti = evento.getMaxPartecipanti();
			int tolleranzaI = evento.getTolleranza();
			boolean scadutoRitiro;
			boolean ritiroPreScadenzaIscrizione;
			
			
			if(evento.getTermineRitiro()!=null) {
				LocalDate termineRitiro = evento.getTermineRitiro();
				scadutoRitiro = ClockUtil.now().isAfter(termineRitiro);
				ritiroPreScadenzaIscrizione = !termineRitiro.equals(scadenzaIscrizione);
			}else {
				scadutoRitiro = false;
				ritiroPreScadenzaIscrizione = false;
			}

			boolean scadutaIscrizione = ClockUtil.now().isAfter(scadenzaIscrizione);
			boolean partecipantiOK = numPartecipanti >= maxPartecipanti
					&& numPartecipanti <= (maxPartecipanti + tolleranzaI);
			boolean isPartecipantiMAX = numPartecipanti == (maxPartecipanti + tolleranzaI);

			if ( (scadutaIscrizione && partecipantiOK) || (ritiroPreScadenzaIscrizione && scadutoRitiro && isPartecipantiMAX) ){
						
				Utente creatoreI = evento.getCreatore();
				SpazioPersonale spazioCreatore = creatoreI.getSpazioP();
				spazioCreatore.removeEventoC(evento.getCode());
				
				evento.setStatoEvento(1); // 1 = chiuso

				for (int j = 0; j < numPartecipanti; j++) {
					Utente utenteDaNotificare = partecipanti.get(j);
					SpazioPersonale spazioDaNotificare = utenteDaNotificare.getSpazioP();
					spazioDaNotificare.addNotifica(ToStringUtil.notificaChiuso(evento, utenteDaNotificare));
				}
			}
		}
	}

	/**
	 * Esegue i tre metodi per cambiare lo stato degli eventi oppurtuni
	 */
	public void aggiorna() {
		eventiChiusi();
		eventiConclusi();
		eventiFalliti();
	}

	/**
	 * Restituisce l'evento presente in raccolta
	 * 
	 * @param code codice evento
	 * @return
	 */
	public Evento getEventoFromCode(int code) {
		for (int i = 0; i < this.raccoltaE.size(); i++) {
			Evento e = this.raccoltaE.get(i);
			int codiceE = e.getCode();
			if (codiceE == code)
				return e;
		}
		return null;
	}

	/**
	 * Verifica se l'evento richiesto è presente nella raccolta
	 * 
	 * @param e evento da verificare se è presente
	 * @return bool che indica se l'evento è presente o meno
	 */
	public boolean eventoPresente(Evento e) {
		for (int i = 0; i < this.raccoltaE.size(); i++) {
			if (e.equals(this.raccoltaE.get(i)))
				return true;
		}
		return false;
	}

	/**
	 * Fornisce l'indice dell'evento richiesto
	 * 
	 * @param e evento di cui si vuole sapere l'indice
	 * @return int che indica l'indice dell'evento
	 */
	public int indiceEvento(Evento e) {
		Integer index = null;
		for (int i = 0; i < this.raccoltaE.size(); i++) {
			if (e.equals(this.raccoltaE.get(i)))
				index = i;
		}
		return index;
	}

	/**
	 * Aggiunge un evento alla raccolta
	 * 
	 * @param daAggiungere evento da aggiungere alla raccolta
	 */
	public void addEvento(Evento daAggiungere) {
		raccoltaE.add(daAggiungere);
		setChanged();
		notifyObservers();
		clearChanged();
	}

	/**
	 * Rimuove l'evento ritirato dalla raccolta
	 * 
	 * @param codiceEvento
	 */
	public void ritiraEvento(int codiceEvento) {
		Evento daRitirare = getEventoFromCode(codiceEvento);
		daRitirare.notificaRitiro();
		raccoltaE.remove(daRitirare);
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	/**
	 * Formatta gli eventi aperti della raccolta ordinandoli per categoria
	 * 
	 * @return String contenente la lista degli eventi aperti formattata
	 */
	public boolean vuoto() {
		for (int i = 0; i < raccoltaE.size(); i++) {
			if (raccoltaE.get(i).getStatoEvento() == 0)
				return false;
		}
		return true;
	}

	public ArrayList<Evento> getRaccoltaE() {
		return raccoltaE;
	}
}