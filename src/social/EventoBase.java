package social;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;
import utility.*;
import java.time.*;

public class EventoBase implements Jsonable, Evento {
	private String nome; // facoltativo
	private int maxPartecipanti;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate scadenzaIscrizione;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataInizio;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataFine; // facoltativo
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate termineRitiro; // facoltativo
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	private LocalTime oraInizio;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	private LocalTime oraFine; // facoltativo
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@JsonDeserialize(using = DurationDeserializer.class)
	@JsonSerialize(using = DurationSerializer.class)
	private Duration durata; // facoltativo
	private String luogo;
	private double quota;
	private String compresoQuota; // facoltativo
	private String note; // facoltativo
	private int tolleranza; // facoltativo
	private Utente creatore;
	private ArrayList<Utente> partecipanti;
	private int code;
	private int tipoEvento; // 0 Partita 1 Motoraduno 2 Gita
	private int statoEvento; // 0 Aperto 1 Chiuso 2 Fallito 3 Concluso 4 Ritirato

	/**
	 * Costrutture dell'evento, prende i parametri obbligatori
	 * 
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
	public EventoBase(int maxPartecipanti, LocalDate scadenzaIscrizione, LocalDate dataInizio, String luogo,
			LocalTime oraInizio, double quota, Utente creatore, int tipoEvento, int codice) {
		this.maxPartecipanti = maxPartecipanti;
		this.scadenzaIscrizione = scadenzaIscrizione;
		this.dataInizio = dataInizio;
		this.luogo = luogo;
		this.oraInizio = oraInizio;
		this.quota = quota;
		this.creatore = creatore;
		this.tipoEvento = tipoEvento;
		this.code = codice;
		partecipanti = new ArrayList<Utente>();

		partecipanti.add(creatore);
		creatore.getSpazioP().addEventoC(code);
		setStatoEvento(0);
	}

	/**
	 * Verifica che l'utente passato come parametro sia tra i partecipanti
	 * 
	 * @param utenteDaVerificare
	 * @return bool che indica se l'utente è tra i partecipanti o meno
	 */
	public boolean isPartecipante(Utente utenteDaVerificare) {
		if (partecipanti.contains(utenteDaVerificare)) {
			return true;
		}
		return false;
	}

	/**
	 * Iscrive l'utente passato all'evento
	 * 
	 * @param utenteDaIscrivere
	 * @pre utente da iscrivere non già partecipante e numero di partecipanti che
	 *      non eccedono massimi partecipanti + tolleranza
	 * @post utente da iscrivere viene aggiunto ai partecipanti e gli viene
	 *       notificato un promemoria
	 */
	public void iscriviUtente(Utente utenteDaIscrivere) throws Exception {
		if (this.termineRitiro != null) {
			if (isPartecipante(utenteDaIscrivere) || ((this.maxPartecipanti + this.tolleranza) == this.partecipanti.size()
					&& this.termineRitiro.isBefore(LocalDate.now())))
				throw new Exception();
		} else {
			if (isPartecipante(utenteDaIscrivere) || ((this.maxPartecipanti + this.tolleranza) == this.partecipanti.size()))
				throw new Exception();
		}
		partecipanti.add(utenteDaIscrivere);
		utenteDaIscrivere.getSpazioP().addNotifica(ToStringUtil.notificaAperto(this, utenteDaIscrivere));
		utenteDaIscrivere.getSpazioP().addEventoP(this.code);
		utenteDaIscrivere.getSpazioP().updated();
		utenteDaIscrivere.getProfilo().addPartecipato(tipoEvento);
	}

	/**
	 * Elimina utente passato partecipante all'evento
	 * 
	 * @param utenteDaRimuovere
	 * @pre utente da eliminare sta partecipando
	 * @post utente da eliminare viene eliminato
	 * @return boolean per confermare eliminazione
	 */
	public void removePartecipante(Utente utenteDaRimuovere) {
		if (isPartecipante(utenteDaRimuovere)) {
			this.getPartecipanti().remove(utenteDaRimuovere);
		}
	}

	/**
	 * Notifica a tutti i partecipanti all'evento che l'evento è stato ritirato
	 * 
	 */
	public void notificaRitiro() {
		for (int i = 0; i < this.partecipanti.size(); i++) {
			Utente daNotificare = this.partecipanti.get(i);
			daNotificare.getSpazioP().addNotifica(ToStringUtil.notificaRitirato(this, this.partecipanti.get(i)));
		}
	}

	public boolean equals(Evento e) {
		if (e.getCode() == this.getCode())
			return true;
		else
			return false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(int tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public int getStatoEvento() {
		return statoEvento;
	}

	public void setStatoEvento(int statoEvento) {
		this.statoEvento = statoEvento;
	}

	public int getMaxPartecipanti() {
		return maxPartecipanti;
	}

	public void setMaxPartecipanti(int maxPartecipanti) {
		this.maxPartecipanti = maxPartecipanti;
	}

	public LocalDate getScadenzaIscrizione() {
		return scadenzaIscrizione;
	}

	public void setScadenzaIscrizione(LocalDate scadenzaIscrizione) {
		this.scadenzaIscrizione = scadenzaIscrizione;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public Duration getDurata() {
		return durata;
	}

	public void setDurata(Duration durata) {
		this.durata = durata;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public LocalTime getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(LocalTime oraInizio) {
		this.oraInizio = oraInizio;
	}

	public LocalTime getOraFine() {
		return oraFine;
	}

	public void setOraFine(LocalTime oraFine) {
		this.oraFine = oraFine;
	}

	public double getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public String getCompresoQuota() {
		return compresoQuota;
	}

	public void setCompresoQuota(String compresoQuota) {
		this.compresoQuota = compresoQuota;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Utente getCreatore() {
		return creatore;
	}

	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}

	public ArrayList<Utente> getPartecipanti() {
		return partecipanti;
	}

	public void setPartecipanti(ArrayList<Utente> partecipanti) {
		this.partecipanti = partecipanti;
	}

	public LocalDate getTermineRitiro() {
		return termineRitiro;
	}

	public void setTermineRitiro(LocalDate termineRitiro) {
		this.termineRitiro = termineRitiro;
	}

	public int getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(int tolleranza) {
		this.tolleranza = tolleranza;
	}

	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public void setCode(int code) {
		this.code = code;
	}

	public EventoBase() {

	}
}