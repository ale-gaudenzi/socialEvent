package social;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import utility.ToStringUtil;

public class EventoQuote extends EventoBase {
	private HashMap<String, Double> quoteUtenti = new HashMap<String, Double>();
	private HashMap<String, Double> quoteAggiuntive;

	/**
	 * Costrutture dell'evento, prende i parametri obbligatori
	 * 
	 * @param quoteAggiuntive
	 * @param maxPartecipanti
	 * @param scadenzaIscrizione
	 * @param dataInizio
	 * @param luogo
	 * @param oraInizio
	 * @param quota
	 * @param creatore
	 * @param tipoEvento
	 * @param codice
	 */
	public EventoQuote(HashMap<String, Double> quoteAggiuntive, int maxPartecipanti, LocalDate scadenzaIscrizione,
			LocalDate dataInizio, String luogo, LocalTime oraInizio, double quota, Utente creatore, int tipoEvento,
			int codice) {
		super(maxPartecipanti, scadenzaIscrizione, dataInizio, luogo, oraInizio, quota, creatore, tipoEvento, codice);
		this.quoteAggiuntive = quoteAggiuntive;
	}
	
	/**
	 * Iscrive l'utente passato all'evento, registrando le quote selezionate dallo stesso
	 * 
	 * @param utenteDaIscrivere
	 * @param quoteSelezionate
	 * @pre utente da iscrivere non già partecipante e numero di partecipanti che
	 *      non eccedono massimi partecipanti + tolleranza
	 * @post utente da iscrivere viene aggiunto ai partecipanti e gli viene
	 *       notificato un promemoria
	 */
	public void iscriviUtente(Utente daIscrivere, HashMap<String, Double> quoteSelezionate) throws Exception {
		double quotaTotale = 0.0;
		for (Map.Entry<String, Double> entry : quoteAggiuntive.entrySet()) {
			if (quoteSelezionate.containsKey(entry.getKey()))
				quotaTotale += entry.getValue();
		}
		this.quoteUtenti.put(daIscrivere.getNome(), quotaTotale);

		if (this.getTermineRitiro() != null) {
			if (isPartecipante(daIscrivere)
					|| ((this.getMaxPartecipanti() + this.getTolleranza()) == this.getPartecipanti().size()
							&& this.getTermineRitiro().isBefore(LocalDate.now())))
				throw new Exception();
		} else {
			if (isPartecipante(daIscrivere)
					|| ((this.getMaxPartecipanti() + this.getTolleranza()) == this.getPartecipanti().size()))
				throw new Exception();
		}
		getPartecipanti().add(daIscrivere);
		daIscrivere.getSpazioP().addNotifica(ToStringUtil.notificaAperto(this, daIscrivere));
		daIscrivere.getSpazioP().addEventoP(getCode());
		daIscrivere.getSpazioP().updated();
		daIscrivere.getProfilo().addPartecipato(getTipoEvento());
	}

	public HashMap<String, Double> getQuoteAggiuntive() {
		return quoteAggiuntive;
	}

	public void setQuoteAggiuntive(HashMap<String, Double> quoteAggiuntive) {
		this.quoteAggiuntive = quoteAggiuntive;
	}

	public HashMap<String, Double> getQuoteUtenti() {
		return quoteUtenti;
	}

	public EventoQuote() {

	}

}
